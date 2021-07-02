package com.xrlj.servicesysoffdct.common.officeclient;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * office处理平台提供商。
 */
@Component
public class OfficeClientFactory implements ApplicationContextAware {

    private static Map<OfficeClientType, OfficeClient> officeClientMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OfficeClient> beans = applicationContext.getBeansOfType(OfficeClient.class);
        officeClientMap = new HashMap<>();
        beans.forEach((key, value) -> {
            officeClientMap.put(value.getOfficeClientType(), value);
        });
    }

    public <T extends OfficeClient> T getOfficeClient(OfficeClientType officeClientType) {
        return (T)officeClientMap.get(officeClientType);
    }

}
