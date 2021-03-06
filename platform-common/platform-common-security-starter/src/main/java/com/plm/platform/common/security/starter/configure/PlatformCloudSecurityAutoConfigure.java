package com.plm.platform.common.security.starter.configure;

import com.plm.platform.common.core.entity.constant.PlatformConstant;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.security.starter.handler.PlatformAccessDeniedHandler;
import com.plm.platform.common.security.starter.handler.PlatformAuthExceptionEntryPoint;
import com.plm.platform.common.security.starter.properties.PlatformCloudSecurityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.Base64Utils;

/**
 * @author crystal
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(PlatformCloudSecurityProperties.class)
@ConditionalOnProperty(value = "platform.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class PlatformCloudSecurityAutoConfigure extends GlobalMethodSecurityConfiguration {

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
    @Primary
    @ConditionalOnMissingBean(DefaultTokenServices.class)
    public PlatformUserInfoTokenServices platformUserInfoTokenServices(ResourceServerProperties properties) {
        return new PlatformUserInfoTokenServices(properties.getUserInfoUri(), properties.getClientId());
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            String gatewayToken = new String(Base64Utils.encode(PlatformConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(PlatformConstant.GATEWAY_TOKEN_HEADER, gatewayToken);
            String authorizationToken = PlatformUtil.getCurrentTokenValue();
            if (StringUtils.isNotBlank(authorizationToken)) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, PlatformConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
