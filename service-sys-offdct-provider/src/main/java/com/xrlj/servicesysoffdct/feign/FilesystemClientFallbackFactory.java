package com.xrlj.servicesysoffdct.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FilesystemClientFallbackFactory implements FallbackFactory<FilesystemClient> {

    @Override
    public FilesystemClient create(Throwable throwable) {
        log.error("》》》请求服务降级",throwable);
        return null;
    }
}
