package com.xrlj.servicesysoffdct.common;

import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VFileUploadReq;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.infrastructure.APIsAssert;
import com.xrlj.utils.security.Base64Utils;
import org.apache.commons.io.FilenameUtils;

public final class UploadFileUtils {

    private UploadFileUtils() {}

    /**
     * 上传本地文件到文件服务器。
     * @param localFilePath 本地文件路径
     * @param fileOriName 文件保存名称。可不带后缀
     * @param filesystemClient 文件服务对象
     * @return 文件网络存储信息。
     */
    public static VSysFileResp uploadFile(String localFilePath, String fileOriName, SysFilesClient filesystemClient) {
        //上传文件到文件系统
        VFileUploadReq vFileUploadReq = new VFileUploadReq();
        vFileUploadReq.setFileBase64Str(Base64Utils.getFileBase64Str(localFilePath));
        String oriName = FilenameUtils.getBaseName(fileOriName).concat(".").concat(FilenameUtils.getExtension(localFilePath));
        vFileUploadReq.setOriName(oriName);
        VSysFileResp fileResp = filesystemClient.uploadBase64(vFileUploadReq);
        if (fileResp == null) {
            throw APIsAssert.fileUploadError();
        }
        return fileResp;
    }
}
