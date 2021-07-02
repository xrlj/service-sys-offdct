// 自动生成。手动添加泛型实体。
package com.xrlj.servicesysoffdct.service;

import com.xrlj.framework.base.BaseService;
import com.xrlj.framework.feign.vo.VSysFileResp;
import com.xrlj.servicesysoffdct.api.vo.req.VGenDocReq;

/**
 * 接口服务。word文档相关处理
 * */
public interface DocWorkerService extends BaseService {

    /**
     * 按doc模板生成word。并返回生成后doc文件所在路径。
     * @param req 模板参数
     * @return  返回生成后doc所在路径信息。
     */
    VSysFileResp genDocByTemplate(VGenDocReq req);

    /**
     * doc文件转成pdf。
     * @param docPath   doc文件路径。本地绝对路径或者网络路径
     * @param fileOriName   文件原始名称
     * @return  返回最终pdf存储路径信息
     */
    VSysFileResp docToPdf(String docPath, String fileOriName);
}
