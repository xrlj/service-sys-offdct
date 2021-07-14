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
public class VPdfKeyCoordinateReq extends VBaseReq {


    /**
     * pdf文件路径。网络url或者本地绝对路径。
     */
    @NotBlank
    private String pdfPath;
    /**
     * 关键字。多个用英文逗号隔开。
     */
    @NotBlank
    private String keys;
}
