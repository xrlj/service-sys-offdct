// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service;

import com.xrlj.framework.base.BaseService;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.vo.VPdfKeyCoordinateResp;
import com.xrlj.servicesysoffdct.api.vo.req.VPdfKeyCoordinateReq;
import com.xrlj.servicesysoffdct.common.ImgType;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 获取pdf关键字所在坐标。
     * @param vo
     * @return
     */
    List<VPdfKeyCoordinateResp> getKeyCoordinate(VPdfKeyCoordinateReq vo);

    /**
     * 图片转pdf。
     * @param imgPath
     * @param fileOriName
     * @return
     */
    VSysFileResp imgToPdf(String imgPath, String fileOriName);

    /**
     * pdf转成img。jpg或者png格式
     * @param pdfPath  pdf 资源统一位置uri
     * @param page  转换页码，null转全部
     * @param imgType   转成图片类型。JPEG或者PNG
     * @param fileOriName   文件保存名称
     * @return
     */
    List<VSysFileResp> pdfToImg(String pdfPath, Integer page, ImgType imgType, String fileOriName);
}
