package com.xrlj.servicesysoffdct.api.vo.req;

import com.xrlj.framework.vo.VBaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToPdfReq extends VBaseReq {

    private static final long serialVersionUID = -7309157582258981283L;

    /**
     * 待转文件路径。本地绝对路径，或者网络路径。
     */
    @NotBlank
    private String oriFilePath;
    /**
     * 文件最终保存原始名称。
     */
    @NotBlank
    private String oriName;

    /**
     * 模板待渲染数据。
     */
    private Map data;
}
