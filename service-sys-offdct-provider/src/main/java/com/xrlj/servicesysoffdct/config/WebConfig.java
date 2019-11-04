package com.xrlj.servicesysoffdct.config;

import com.xrlj.framework.config.AbstractInnerWebConfiguration;
import com.xrlj.framework.config.JsonHandlerExceptionResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

@Configuration
public class WebConfig extends AbstractInnerWebConfiguration {


    /**
     * 定义拦截器。
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

}
