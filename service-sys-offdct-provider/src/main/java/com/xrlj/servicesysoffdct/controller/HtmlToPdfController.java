package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.servicesysoffdct.api.HtmlToPdfApi;
import com.xrlj.servicesysoffdct.common.CmdExecUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class HtmlToPdfController extends BaseController implements HtmlToPdfApi {

    @Override
    public String test() {
        String cmd = "wkhtmltopdf baidu.com baidu.pdf";
        CmdExecUtils.execCommond(cmd);
        return null;
    }
}
