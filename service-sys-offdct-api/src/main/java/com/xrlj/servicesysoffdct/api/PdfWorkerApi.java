package com.xrlj.servicesysoffdct.api;

import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.vo.VPdfKeyCoordinateResp;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfKeyCoordinateReq;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfToImgReq;
import com.xrlj.servicesysoffdct.api.vo.req.VToPdfReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * pdf文档处理。
 */
@RequestMapping("/pdfWorker")
public interface PdfWorkerApi {

    /**
     * 获取pdf关键字所在坐标。
     * @param vo 参数对象{@linkplain VPdfKeyCoordinateResp}
     * @return 返回关键字坐标信息。
     */
    @PostMapping("/getKeyCoordinate")
    List<VPdfKeyCoordinateResp> getKeyCoordinate(@RequestBody VPdfKeyCoordinateReq vo);

    /**
     * 图片转pdf。图片格式jpg、png
     * @param vo 参数对象。
     * @return
     */
    @PostMapping("/imgToPdf")
    VSysFileResp imgToPdf(@RequestBody VToPdfReq vo);

    /**
     * pdf转图片。图片格式jpg或者png。
     * @param vo 参数对象{@linkplain VPdfToImgReq}
     * @return 转后图片存储信息列表。
     */
    @PostMapping("/pdfToImg")
    List<VSysFileResp> pdfToImg(@RequestBody VPdfToImgReq vo);
}
