package com.xrlj.servicesysoffdct.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * html转换成pdf。
 */
@RequestMapping("/htmlToPdf}")
public interface HtmlToPdfApi {

    @GetMapping("/test")
    String test();
}
