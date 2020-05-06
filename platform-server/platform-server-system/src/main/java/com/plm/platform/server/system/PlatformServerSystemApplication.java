package com.plm.platform.server.system;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author crystal
 */
@EnableAsync
@SpringBootApplication
@EnablePlatformCloudResourceServer
@EnableTransactionManagement
@EnableDistributedTransaction
@MapperScan("com.plm.platform.server.system.mapper")
public class PlatformServerSystemApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerSystemApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
