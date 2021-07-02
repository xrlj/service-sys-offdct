package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.PdfWorkerApi;
import com.xrlj.servicesysoffdct.api.vo.req.ToPdfReq;
import com.xrlj.servicesysoffdct.service.PdfWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class PdfWorkerController extends BaseController implements PdfWorkerApi {

    private final PdfWorkerService pdfWorkerService;

    public PdfWorkerController(PdfWorkerService pdfWorkerService) {
        this.pdfWorkerService = pdfWorkerService;
    }
}
