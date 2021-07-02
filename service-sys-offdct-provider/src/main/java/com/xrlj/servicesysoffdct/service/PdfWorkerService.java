// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service;

import com.xrlj.framework.base.BaseService;
import com.xrlj.framework.feign.vo.VSysFileResp;

import java.util.Map;

/**
 * 接口服务 */
public interface PdfWorkerService extends BaseService {

    /**
     * pdf转work。
     * @param pdfPath
     * @param fileOriName
     * @return
     */
    VSysFileResp pdfToWork(String pdfPath, String fileOriName);

    /**
     * html模板渲染并转pdf。
     * @param htmlTemplateUrl html模板文件网络路径
     * @param data  模板渲染数据
     * @param fileOriName   文件最终保存原始名称
     * @return pdf文件保存信息
     */
    VSysFileResp htmlTemplateToPdf(String htmlTemplateUrl, Map data, String fileOriName);

    /**
     * ftl模板渲染并转pdf。
     * @param ftlTemplateUrl ftl模板文件网络路径
     * @param data  模板渲染数据
     * @param fileOriName   件最终保存原始名称
     * @return  pdf文件保存信息
     */
    VSysFileResp ftlTemplateToPdf(String ftlTemplateUrl, Map data, String fileOriName);
}
