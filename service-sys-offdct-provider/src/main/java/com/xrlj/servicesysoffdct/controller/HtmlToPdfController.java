package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.servicesysoffdct.api.HtmlToPdfApi;
import com.xrlj.servicesysoffdct.common.CmdExecUtils;
import com.xrlj.servicesysoffdct.feign.FilesystemClient;
import com.xrlj.servicesysoffdct.feign.vo.VFileUploadReq;
import com.xrlj.servicesysoffdct.feign.vo.VSysFileResp;
import com.xrlj.utils.security.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
@RefreshScope
public class HtmlToPdfController extends BaseController implements HtmlToPdfApi {

    @Autowired
    private FilesystemClient filesystemClient;

    @Override
    public String test() {
        String filePath = getAppTmpFileDir().concat(File.separator).concat("baidu.pdf");
        String cmd = "D:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe baidu.com ".concat(filePath);
        boolean b = CmdExecUtils.execCommond(cmd);
        if (b) {
            String fileBase64Str = Base64Utils.getFileBase64Str(filePath);
            VFileUploadReq vFileUploadReq = new VFileUploadReq();
            vFileUploadReq.setTag("wkpdf");
            vFileUploadReq.setDescription("测试");
            vFileUploadReq.setOriName("bd.pdf");
            vFileUploadReq.setFileBase64Str(fileBase64Str);
            VSysFileResp r = filesystemClient.uploadBase64(vFileUploadReq);
            if (r != null) {
                return r.getUrl();
            }
        }
        return null;
    }
}
