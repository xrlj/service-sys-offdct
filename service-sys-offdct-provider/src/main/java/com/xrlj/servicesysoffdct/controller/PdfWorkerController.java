package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.PdfWorkerApi;
import com.xrlj.servicesysoffdct.api.vo.VPdfKeyCoordinateResp;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfKeyCoordinateReq;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfToImgReq;
import com.xrlj.servicesysoffdct.api.vo.req.VToPdfReq;
import com.xrlj.servicesysoffdct.common.ImgType;
import com.xrlj.servicesysoffdct.service.PdfWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RefreshScope
public class PdfWorkerController extends BaseController implements PdfWorkerApi {

    private final PdfWorkerService pdfWorkerService;

    public PdfWorkerController(PdfWorkerService pdfWorkerService) {
        this.pdfWorkerService = pdfWorkerService;
    }

    @Override
    public List<VPdfKeyCoordinateResp> getKeyCoordinate(VPdfKeyCoordinateReq vo) {
        return pdfWorkerService.getKeyCoordinate(vo);
    }

    @Override
    public VSysFileResp imgToPdf(VToPdfReq vo) {
        return pdfWorkerService.imgToPdf(vo.getOriFilePath(), vo.getOriName());
    }

    @Override
    public List<VSysFileResp> pdfToImg(VPdfToImgReq vo) {
        List<VSysFileResp> resp = pdfWorkerService.pdfToImg(vo.getPdfPath(), vo.getPage(), ImgType.valueOf(vo.getImgType()), vo.getFileOriName());
        return resp;
    }
}
