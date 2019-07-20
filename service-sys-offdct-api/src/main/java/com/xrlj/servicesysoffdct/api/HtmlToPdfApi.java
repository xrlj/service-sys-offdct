package com.xrlj.servicesysoffdct.api;

import com.xrlj.servicesysoffdct.api.vo.VPdfInfoResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @GetMapping("/convToPdf")
//    VPdfInfoResp convToPdf();
}
