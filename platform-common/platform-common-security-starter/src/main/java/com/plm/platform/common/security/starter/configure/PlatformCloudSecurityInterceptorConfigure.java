package com.plm.platform.common.security.starter.configure;

import com.plm.platform.common.security.starter.interceptor.PlatformServerProtectInterceptor;
import com.plm.platform.common.security.starter.properties.PlatformCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author crystal
 */
public class PlatformCloudSecurityInterceptorConfigure implements WebMvcConfigurer {

    private PlatformCloudSecurityProperties properties;

    @Autowired
    public void setProperties(PlatformCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor platformServerProtectInterceptor() {
        PlatformServerProtectInterceptor platformServerProtectInterceptor = new PlatformServerProtectInterceptor();
        platformServerProtectInterceptor.setProperties(properties);
        return platformServerProtectInterceptor;
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(platformServerProtectInterceptor());
    }
}
