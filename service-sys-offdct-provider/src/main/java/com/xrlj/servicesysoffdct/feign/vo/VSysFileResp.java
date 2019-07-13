package com.xrlj.servicesysoffdct.feign.vo;

import com.xrlj.framework.vo.VBaseResp;
import lombok.Data;

@Data
public class VSysFileResp extends VBaseResp {

    /**
     * 文件存储id。
     */
    private long id;

    /**
     * 文件原始名称。
     */
    private String oriName;

    /**
     * 文件所在网络全路径。
     */
    private String url;
}
