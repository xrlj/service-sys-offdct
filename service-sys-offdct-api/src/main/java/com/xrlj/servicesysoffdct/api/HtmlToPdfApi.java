package com.xrlj.servicesysoffdct.api;

import com.xrlj.servicesysoffdct.api.vo.VPdfInfoResp;
import com.xrlj.servicesysoffdct.api.vo.req.VRenderingHtmlToPdfReq;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * html转换成pdf。
 */
@RequestMapping("/htmlToPdf")
public interface HtmlToPdfApi {

    @GetMapping("/test")
    String test();

    @GetMapping("/test2")
    String test2() throws IOException;
}
