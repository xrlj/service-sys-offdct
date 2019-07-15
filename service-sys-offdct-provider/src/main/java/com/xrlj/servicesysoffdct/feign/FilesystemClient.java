package com.xrlj.servicesysoffdct.feign;

import com.xrlj.servicesysoffdct.feign.vo.VFileUploadReq;
import com.xrlj.servicesysoffdct.feign.vo.VSysFileResp;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "service-sys-filesystem", configuration = FilesystemClient.MultipartSupportConfig.class, fallbackFactory = FilesystemClientFallbackFactory.class)
public interface FilesystemClient {

    @PostMapping(value = "/sysFiles/upload")
    VSysFileResp upload(@RequestPart("file") MultipartFile file);

    @PostMapping(value = "/sysFiles/uploadBase64")
    VSysFileResp uploadBase64(@RequestBody VFileUploadReq vFileUploadReq);

    @PostMapping(value = "/sysFiles/uploadBytes", headers = {"Content-Type=multipart/form-data"})
    VSysFileResp uploadBytes(@RequestParam("fileData") byte[] fileData, @RequestParam("fileName") String fileName);

    class MultipartSupportConfig {

        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder () {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}

