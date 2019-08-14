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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "service-sys-filesystem", configuration = FilesystemClient.MultipartSupportConfig.class, fallbackFactory = FilesystemClientFallbackFactory.class)
public interface FilesystemClient {

    @RequestMapping(value = "/sysFiles/upload", method = RequestMethod.POST)
    VSysFileResp upload(@RequestPart("file") MultipartFile file);

    @RequestMapping(value = "/sysFiles/uploadBase64", method = RequestMethod.POST)
    VSysFileResp uploadBase64(@RequestBody VFileUploadReq vFileUploadReq);

    @RequestMapping(value = "/sysFiles/uploadBytes", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
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

