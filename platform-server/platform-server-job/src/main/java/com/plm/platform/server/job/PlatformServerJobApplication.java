package com.plm.platform.server.job;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author crystal
 */
@SpringBootApplication
@EnablePlatformCloudResourceServer
@MapperScan("com.plm.platform.server.job.mapper")
public class PlatformServerJobApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerJobApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
