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

    /**
     * 渲染thymeleaf的html后转成pdf，并返回相关信息。
     * @param vo
     * @return
     */
    @PostMapping(value = "/renderingHtmlToPdf")
    VPdfInfoResp renderingHtmlToPdf(@RequestBody @Valid VRenderingHtmlToPdfReq vo);

    /**
     * 把已经渲染后的html文件转成pdf，并返回相关信息。
     * @param renderedHtmlFileUri 已经渲染后的html文件网络路径。thymeleaf,freemarker,等渲染后的html都可以。
     * @return
     */
    @PostMapping(value = "/renderedHtmlToPdf")
    VPdfInfoResp renderedHtmlToPdf(@RequestParam String renderedHtmlFileUri);

    @GetMapping("/test")
    String test();

    @GetMapping("/test2")
    String test2() throws IOException;
}
