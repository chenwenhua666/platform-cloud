package com.plm.platform.common.doc.gateway.configure;

import com.plm.platform.common.doc.gateway.filter.PlatformDocGatewayHeaderFilter;
import com.plm.platform.common.doc.gateway.handler.PlatformDocGatewayHandler;
import com.plm.platform.common.doc.gateway.properties.PlatformDocGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

/**
 * @author crystal
 */
@Configuration
@EnableConfigurationProperties(PlatformDocGatewayProperties.class)
@ConditionalOnProperty(value = "platform.doc.gateway.enable", havingValue = "true", matchIfMissing = true)
public class PlatformDocGatewayAutoConfigure {

    private final PlatformDocGatewayProperties platformDocGatewayProperties;
    private SecurityConfiguration securityConfiguration;
    private UiConfiguration uiConfiguration;

    public PlatformDocGatewayAutoConfigure(PlatformDocGatewayProperties platformDocGatewayProperties) {
        this.platformDocGatewayProperties = platformDocGatewayProperties;
    }

    @Autowired(required = false)
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Autowired(required = false)
    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    @Bean
    public PlatformDocGatewayResourceConfigure platformDocGatewayResourceConfigure(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        return new PlatformDocGatewayResourceConfigure(routeLocator, gatewayProperties);
    }

    @Bean
    public PlatformDocGatewayHeaderFilter platformDocGatewayHeaderFilter() {
        return new PlatformDocGatewayHeaderFilter();
    }

    @Bean
    public PlatformDocGatewayHandler platformDocGatewayHandler(SwaggerResourcesProvider swaggerResources) {
        PlatformDocGatewayHandler platformDocGatewayHandler = new PlatformDocGatewayHandler();
        platformDocGatewayHandler.setSecurityConfiguration(securityConfiguration);
        platformDocGatewayHandler.setUiConfiguration(uiConfiguration);
        platformDocGatewayHandler.setSwaggerResources(swaggerResources);
        platformDocGatewayHandler.setProperties(platformDocGatewayProperties);
        return platformDocGatewayHandler;
    }
}
