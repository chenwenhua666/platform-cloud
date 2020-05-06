package com.plm.platform.common.security.starter.configure;

import com.plm.platform.common.core.entity.constant.PlatformConstant;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.security.starter.handler.PlatformAccessDeniedHandler;
import com.plm.platform.common.security.starter.handler.PlatformAuthExceptionEntryPoint;
import com.plm.platform.common.security.starter.properties.PlatformCloudSecurityProperties;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 * @author crystal
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(PlatformCloudSecurityProperties.class)
@ConditionalOnProperty(value = "platform.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class PlatformCloudSecurityAutoconfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public PlatformAccessDeniedHandler accessDeniedHandler() {
        return new PlatformAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public PlatformAuthExceptionEntryPoint authenticationEntryPoint() {
        return new PlatformAuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PlatformCloudSecurityInterceptorConfigure platformCloudSecurityInterceptorConfigure() {
        return new PlatformCloudSecurityInterceptorConfigure();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(PlatformConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(PlatformConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = PlatformUtil.getCurrentTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, PlatformConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}
