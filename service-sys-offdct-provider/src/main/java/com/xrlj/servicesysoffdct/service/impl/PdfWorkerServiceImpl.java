// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service.impl;

import com.xrlj.framework.base.BaseServiceImpl;
import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VFileUploadReq;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.framework.spring.mvc.api.APIs;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientFactory;
import com.xrlj.servicesysoffdct.common.officeclient.OfficeClientType;
import com.xrlj.servicesysoffdct.service.PdfWorkerService;
import com.xrlj.utils.security.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * 接口实现
 */
@Slf4j
@Service
@RefreshScope
public class PdfWorkerServiceImpl extends BaseServiceImpl implements PdfWorkerService {

    private final SysFilesClient filesystemClient;
    private final OfficeClientFactory officeClientFactory;

    public PdfWorkerServiceImpl(SysFilesClient filesystemClient, OfficeClientFactory officeClientFactory) {
        this.filesystemClient = filesystemClient;
        this.officeClientFactory = officeClientFactory;
    }

    @Override
    public VSysFileResp htmlTemplateToPdf(String htmlTemplateUrl, Map data, String fileOriName) {
        return null;
    }

    @Override
    public VSysFileResp ftlTemplateToPdf(String ftlTemplateUrl, Map data, String fileOriName) {
        return null;
    }
}
