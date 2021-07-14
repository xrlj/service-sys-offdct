package com.xrlj.servicesysoffdct.common.officeclient;

import com.itextpdf.io.image.ImageType;
import com.xrlj.servicesysoffdct.common.ImgType;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface OfficeClient {

    OfficeClientType getOfficeClientType();

    /**
     * doc转pdf。
     * @param docPath doc文件路径。本地绝对路径或者网络路径。
     * @return 返回生成后的pdf文件或者返回null
     */
    File docToPdf(String docPath);

    /**
     * doc按模板填充，导出。
     * @param docTemplatePath   doc模板本地绝对路径或者网络路径。
     * @param data  模板待渲染数据
     * @return 返回渲染后doc文件或者返回null
     */
    File genDocByTemplate(String docTemplatePath, Map data);

    /**
     * pdf转图片。目前实现转成jpg或者png格式。
     * @param pdfPath
     * @param page 转换指定页，null则转换全部页码
     * @param resolution 图片解析度，默认300
     * @param imgType
     * @return
     */
    List<File> pdfToImg(String pdfPath, Integer page, Integer resolution,  ImgType imgType) throws Exception;
}
