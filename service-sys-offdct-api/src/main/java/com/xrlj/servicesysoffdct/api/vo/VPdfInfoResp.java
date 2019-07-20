package com.xrlj.servicesysoffdct.api.vo;

import com.xrlj.framework.vo.VBaseResp;
import lombok.Data;

@Data
public class VPdfInfoResp extends VBaseResp {

    /**
     * 文件存储id。
     */
    private long id;

    /**
     * 生成的pdf的访问路径。
     */
    private String pdfUrl;
}
