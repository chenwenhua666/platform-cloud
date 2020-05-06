package com.plm.platform.common.security.starter.configure;

import com.plm.platform.common.security.starter.interceptor.PlatformServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author crystal
 */
public class PlatformCloudSecurityInterceptorConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor platformServerProtectInterceptor() {
        return new PlatformServerProtectInterceptor();
    }

    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(platformServerProtectInterceptor());
    }
}
