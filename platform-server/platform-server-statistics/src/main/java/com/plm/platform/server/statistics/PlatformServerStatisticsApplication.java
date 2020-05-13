package com.plm.platform.server.statistics;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnablePlatformCloudResourceServer
@EnableFeignClients
@EnableTransactionManagement
public class PlatformServerStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformServerStatisticsApplication.class, args);
	}

}
