package com.xrlj.servicesysoffdct.api.vo.req;

import com.xrlj.framework.vo.VBaseReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VPdfToImgReq extends VBaseReq {

    private static final long serialVersionUID = -2515762297537614623L;
    /**
     * 待转文件路径。统一资源路径URI.
     */
    @NotBlank
    private String pdfPath;
    /**
     * 文件最终保存原始名称。
     */
    @NotBlank
    private String fileOriName;
    /**
     * 要转img格式。传JPEG或者PNG.
     */
    @NotBlank
    private String imgType;
    /**
     * 转pdf页码。不传，转全部页码为img。
     */
    private Integer page;
}
