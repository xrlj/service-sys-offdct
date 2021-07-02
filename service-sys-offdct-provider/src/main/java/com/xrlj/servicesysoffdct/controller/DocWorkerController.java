package com.xrlj.servicesysoffdct.controller;

import com.xrlj.framework.base.BaseController;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.DocWorkerApi;
import com.xrlj.servicesysoffdct.api.vo.req.ToPdfReq;
import com.xrlj.servicesysoffdct.api.vo.req.VGenDocReq;
import com.xrlj.servicesysoffdct.service.DocWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class DocWorkerController extends BaseController implements DocWorkerApi {

    private final DocWorkerService docWorkerService;

    public DocWorkerController(DocWorkerService docWorkerService) {
        this.docWorkerService = docWorkerService;
    }

    @Override
    public VSysFileResp genDocByTemplate(VGenDocReq vGenDocReq) {
        return docWorkerService.genDocByTemplate(vGenDocReq);
    }

    @Override
    public VSysFileResp docToPdf(ToPdfReq req) {
        return docWorkerService.docToPdf(req.getOriFilePath(), req.getOriName());
    }

    /*
    * List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("sort", 1);
        m1.put("contractName", "江门新会骏凯豪庭五期一标A区交付区项目园林绿化（生态）建设工程施工合同");
        m1.put("contractCode", "江门新会骏凯豪庭-工程施工类-配套工程类-2021-0156");
        m1.put("coreName", "江门保利项目公司");
        m1.put("supplier","广州链金科技");
        m1.put("invoiceCode","31502316");
        m1.put("invoiceMoney","9,069,524.00");
        m1.put("money","7,000,000.00");
        m1.put("expirDate","2022-06-22");
        m1.put("remark","/");

        Map<String, Object> m2 = new HashMap<>();
        m2.put("sort", 2);
        m2.put("contractName", "大师傅士大夫士大夫萨芬士大夫");
        m2.put("contractCode", "江门新会骏凯");
        m2.put("coreName", "江门保利项目");
        m2.put("supplier","小草");
        m2.put("invoiceCode","31502316");
        m2.put("invoiceMoney","9,069,524.00");
        m2.put("money","7,000,000.00");
        m2.put("expirDate","2022-06-22");
        m2.put("remark","/");

        list.add(m1);
        list.add(m2);

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("fdList", policy).build();
        //模板数据
        HashMap<String, Object> data = new HashMap<>() {{
            put("code", "abc-1");
            put("fdList", list);
        }};
        String dockTemplateUrl = "F:\\xrlj.github.com\\service-sys-offdct\\service-sys-offdct-provider\\template.docx";
        XWPFTemplate template = XWPFTemplate.compile(dockTemplateUrl, config).render(data);
        try {
            template.writeAndClose(new FileOutputStream("F:\\output.docx"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "success";*/
}
