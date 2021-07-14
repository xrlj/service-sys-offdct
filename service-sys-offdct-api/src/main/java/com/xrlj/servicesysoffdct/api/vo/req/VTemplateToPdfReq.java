package com.xrlj.servicesysoffdct.api.vo.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VTemplateToPdfReq extends VToPdfReq {

    private static final long serialVersionUID = 8478636760881524424L;
    /**
     * 模板渲染数据
     */
    @NotNull
    private Map data;
}
