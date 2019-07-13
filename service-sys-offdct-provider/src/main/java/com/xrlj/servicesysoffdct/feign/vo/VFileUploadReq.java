package com.xrlj.servicesysoffdct.feign.vo;

import com.xrlj.framework.vo.VBaseReq;
import lombok.Data;

@Data
public class VFileUploadReq extends VBaseReq {

    /**
     * 原始名称
     */
    private String oriName;

    /**
     * 标志
     */
    private String tag;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 文件base64内容。
     */
    private String fileBase64Str;
}
