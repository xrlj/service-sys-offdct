package com.xrlj.servicesysoffdct.api;

import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.vo.req.ToPdfReq;
import com.xrlj.servicesysoffdct.api.vo.req.VGenDocReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * word文本处理。
 */
@RequestMapping("/docWorker")
public interface DocWorkerApi {

    /**
     * doc模板渲染数据生成doc文档。
     * @param vGenDocReq
     * @return doc网络存储信息
     */
    @PostMapping("/genDocByTemplate")
    VSysFileResp genDocByTemplate(@RequestBody VGenDocReq vGenDocReq);

    /**
     * doc转pdf。
     * @param req 参数对象
     * @return pdf网络存储信息
     */
    @PostMapping("/docToPdf")
    VSysFileResp docToPdf(@RequestBody ToPdfReq req);
}
