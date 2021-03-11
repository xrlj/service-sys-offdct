package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.framework.feign.SysFilesClient;
import com.xrlj.framework.feign.vo.VFileUploadReq;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.HtmlToPdfApi;
import com.xrlj.servicesysoffdct.common.CmdExecUtils;
import com.xrlj.utils.security.Base64Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RefreshScope
public class HtmlToPdfController extends BaseController implements HtmlToPdfApi {

    @Autowired
    private SysFilesClient filesystemClient;

    @Override
    public String test() {
        String filePath = getAppTmpFileDir().concat(File.separator).concat("baidu.pdf");
        String cmd = "wkhtmltopdf baidu.com  ".concat(filePath);
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

//        String fileBase64Str = Base64Utils.getFileBase64Str("C:\\Users\\xinxiamu\\Pictures\\微信图片.jpg");
//        VFileUploadReq vFileUploadReq = new VFileUploadReq();
//        vFileUploadReq.setTag("wkpdf");
//        vFileUploadReq.setDescription("测试");
//        vFileUploadReq.setOriName("微信图片.jpg");
//        vFileUploadReq.setFileBase64Str(fileBase64Str);
//        VSysFileResp r = filesystemClient.uploadBase64(vFileUploadReq);
//        if (r != null) {
//            return r.getUrl();
//        }

        return null;
    }

    @Override
    public String test2() throws IOException {
//        String filePath = getAppTmpFileDir().concat(File.separator).concat("bd.pdf");
//        String cmd = "D:\\wkhtmltopdf\\bin\\wkhtmltopdf.exe baidu.com ".concat(filePath);
//        boolean bb = CmdExecUtils.execCommond(cmd);
//        if (bb) {
//            byte[] data = FileUtils.readFileToByteArray(new File(filePath));
//            VSysFileResp r = filesystemClient.uploadBytes(data, "bd.pdf");
//            if (r != null) {
//                return r.getUrl();
//            }
//        }
        byte[] data = FileUtils.readFileToByteArray(new File("C:\\Users\\zmt\\Pictures\\Saved Pictures\\black.jpg"));
        VSysFileResp r = filesystemClient.uploadBytes(data, "black.jpg");
        if (r != null) {
            return r.getUrl();
        }
        return null;
    }
}
