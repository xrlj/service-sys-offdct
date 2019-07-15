package com.xrlj.servicesysoffdct.feign;

import com.xrlj.servicesysoffdct.feign.vo.VFileUploadReq;
import com.xrlj.servicesysoffdct.feign.vo.VSysFileResp;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class FilesystemClientFallbackFactory implements FallbackFactory<FilesystemClient> {

    @Override
    public FilesystemClient create(Throwable throwable) {
        log.error("》》》请求服务降级",throwable);
        return new FilesystemClient() {
            @Override
            public VSysFileResp upload(MultipartFile file) {
                return new VSysFileResp();
            }

            @Override
            public VSysFileResp uploadBase64(VFileUploadReq vFileUploadReq) {
                return new VSysFileResp();
            }

            @Override
            public VSysFileResp uploadBytes(byte[] fileData, String fileName) {
                return new VSysFileResp();
            }
        };
    }
}
