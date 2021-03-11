package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.servicesysoffdct.api.PdfWorkerApi;
import com.xrlj.servicesysoffdct.api.vo.VPdfInfoResp;
import com.xrlj.servicesysoffdct.api.vo.req.VRenderingHtmlToPdfReq;
import com.xrlj.servicesysoffdct.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RefreshScope
public class PdfWorkerController extends BaseController implements PdfWorkerApi {
    @Override
    public VPdfInfoResp renderingHtmlToPdf(@Valid VRenderingHtmlToPdfReq vo) {
//        Constants.getMuPDFToolsPath()
        return null;
    }

    @Override
    public VPdfInfoResp renderedHtmlToPdf(String renderedHtmlFileUri) {
        return null;
    }
}
