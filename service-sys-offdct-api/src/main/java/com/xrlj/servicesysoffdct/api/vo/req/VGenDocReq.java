package com.xrlj.servicesysoffdct.api.vo.req;

import com.xrlj.framework.vo.VBaseReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VGenDocReq extends VBaseReq {

    private static final long serialVersionUID = 1970194200326297854L;

    /**
     * 文件原始名称。不要带后缀
     */
    @NotBlank(message = "文件原始名称非空")
    private String oriName;

    /**
     * doc模板路径。
     */
    @NotBlank(message = "docx模板路径非空")
    private String docTemplateUrl;
    /**
     * 模板渲染数据。表格列表，预留三个，key分别是list1、list2、list3
     */
    @NotBlank(message = "模板数据非空")
    private Map data;
}
