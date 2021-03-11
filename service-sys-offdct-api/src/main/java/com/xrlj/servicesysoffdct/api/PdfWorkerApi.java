package com.xrlj.servicesysoffdct.api;

import com.xrlj.servicesysoffdct.api.vo.VPdfInfoResp;
import com.xrlj.servicesysoffdct.api.vo.req.VRenderingHtmlToPdfReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * html转换成pdf。
 */
@RequestMapping("/pdfWorker")
public interface PdfWorkerApi {

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
}
