package com.plm.platform.server.foundation;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author crystal
 */
@EnableFeignClients
@SpringBootApplication
@EnablePlatformCloudResourceServer
@EnableTransactionManagement
// @EnableDistributedTransaction
@MapperScan("com.plm.platform.server.foundation.mapper")
public class PlatformServerFoundationApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PlatformServerFoundationApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
