// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service.impl;

import com.xrlj.framework.base.BaseServiceImpl;
import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VFileUploadReq;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.framework.spring.mvc.api.APIs;
import com.xrlj.infrastructure.APIsAssert;
import com.xrlj.servicesysoffdct.api.vo.req.VGenDocReq;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientFactory;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientType;
import com.xrlj.servicesysoffdct.service.DocWorkerService;
import com.xrlj.utils.security.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

/**
 * 接口实现
 */
@Slf4j
@Service
@RefreshScope
public class DocWorkerServiceImpl extends BaseServiceImpl implements DocWorkerService {

    @Value("${office.client.platform.gen-doc}")
    private String officeClientPlatformGenDoc;

    @Value("${office.client.platform.doc-to-pdf}")
    private String officeClientPlatformDocToPdf;

    private final SysFilesClient filesystemClient;
    private final OfficeClientFactory officeClientFactory;

    public DocWorkerServiceImpl(SysFilesClient filesystemClient, OfficeClientFactory officeClientFactory) {
        this.filesystemClient = filesystemClient;
        this.officeClientFactory = officeClientFactory;
    }

    @Override
    public VSysFileResp genDocByTemplate(VGenDocReq req) {
        Objects.requireNonNull(req);
        APIsAssert.fieldNotNull(req.getDocTemplateUrl(), "docTemplateUrl");
        APIsAssert.fieldNotNull(req.getData(), "data");
        APIsAssert.fieldNotNull(req.getOriName(), "oriName");
        String ets = FilenameUtils.getExtension(req.getDocTemplateUrl());
        if (!ets.startsWith("docx")) {
            throw APIs.error(1001, "word类型错误，必须docx类型", null);
        }

        VSysFileResp docxFileResp = null;
        File docFile = null;
        try {
            docFile = officeClientFactory.getOfficeClient(OfficeClientType.valueOf(officeClientPlatformGenDoc))
                    .genDocByTemplate(req.getDocTemplateUrl(), req.getData());
            if (docFile == null) {
                throw APIs.error(1002, "doc渲染生成失败", null);
            }
            //上传文件
            VFileUploadReq vFileUploadReq = new VFileUploadReq();
            vFileUploadReq.setFileBase64Str(Base64Utils.getFileBase64Str(docFile.getPath()));
            String oriName = FilenameUtils.getBaseName(req.getOriName()).concat(".").concat(FilenameUtils.getExtension(docFile.getPath()));
            vFileUploadReq.setOriName(oriName);
            docxFileResp = filesystemClient.uploadBase64(vFileUploadReq);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (docFile != null && docFile.isFile()) {
                docFile.delete();
            }
        }

        if (null == docxFileResp) {
            throw APIs.error(1003, "生成docx保存失败", null);
        }

        return docxFileResp;
    }

    @Override
    public VSysFileResp docToPdf(String docPath, String fileOriName) {
        String fileEts = FilenameUtils.getExtension(docPath);
        if (!fileEts.startsWith("doc") && !fileEts.startsWith("docx")) {
            throw APIs.error(1001, "文件格式错误", null);
        }

        File pdfFile = null;
        VSysFileResp fileResp = null;
        try {
            pdfFile = officeClientFactory.getOfficeClient(OfficeClientType.valueOf(officeClientPlatformDocToPdf))
                    .docToPdf(docPath);
            if (pdfFile == null) {
                throw APIs.error(1003, "doc转pdf失败", null);
            }
            //上传文件到文件系统
            VFileUploadReq vFileUploadReq = new VFileUploadReq();
            vFileUploadReq.setFileBase64Str(Base64Utils.getFileBase64Str(pdfFile.getPath()));
            String oriName = FilenameUtils.getBaseName(fileOriName).concat(".").concat(FilenameUtils.getExtension(pdfFile.getPath()));
            vFileUploadReq.setOriName(oriName);
            fileResp = filesystemClient.uploadBase64(vFileUploadReq);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (pdfFile != null && pdfFile.isFile()) {
                pdfFile.delete();
            }
        }

        if (null == fileResp) {
            throw APIs.error(1005, "doc转pdf流程失败", null);
        }

        return fileResp;
    }
}
