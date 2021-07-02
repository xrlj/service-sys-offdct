package com.xrlj.servicesysoffdct.api;

import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.vo.req.ToPdfReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * pdf文档处理。
 */
@RequestMapping("/pdfWorker")
public interface PdfWorkerApi {
}
