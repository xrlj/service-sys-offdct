package com.xrlj.servicesysoffdct.api.vo.req;

import com.xrlj.framework.vo.VBaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VRenderingHtmlToPdfReq extends VBaseReq {

    private static final long serialVersionUID = -7309157582258981283L;

    /**
     * 需要渲染的thymeleaf的html原始文件的网络路径。
     */
    @NotBlank(message = "待thymeleaf渲染html文件网络路径非空")
    private String renderingHtmlFileUri;
    /**
     * 需要渲染的数据。json字符串格式。
     */
    @NotBlank(message = "待渲染数据非空")
    private String dataJson;
}
