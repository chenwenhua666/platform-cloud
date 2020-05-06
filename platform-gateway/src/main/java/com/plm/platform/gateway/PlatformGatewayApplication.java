package com.plm.platform.gateway;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author crystal
 */
@SpringBootApplication
public class PlatformGatewayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformGatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
