package com.xrlj.servicesysoffdct.common.officeclient;

import java.io.File;
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
}
