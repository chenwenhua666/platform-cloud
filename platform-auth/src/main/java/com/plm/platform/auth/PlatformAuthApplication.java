package com.plm.platform.auth;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author crystal
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnablePlatformCloudResourceServer
@MapperScan("com.plm.platform.auth.mapper")
public class PlatformAuthApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformAuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
