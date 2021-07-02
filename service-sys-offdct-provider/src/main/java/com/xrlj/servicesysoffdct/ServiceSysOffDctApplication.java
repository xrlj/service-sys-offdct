package com.xrlj.servicesysoffdct;

import com.xrlj.framework.base.BaseSpringbootApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.xrlj.servicesysoffdct", "com.xrlj.framework"})
@EnableDiscoveryClient
@EnableEurekaClient //可注册到服务中心
@EnableFeignClients(basePackages = {"com.xrlj.framework.feign"})
@EnableCircuitBreaker
@EnableRedisHttpSession
@RefreshScope
public class ServiceSysOffDctApplication extends BaseSpringbootApplication {

    private static String clazzName  = ServiceSysOffDctApplication.class.getSimpleName();

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServiceSysOffDctApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        log.info(">>>>>服务{}启动成功：{}", clazzName,args);
    }
}
