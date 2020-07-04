package com.plm.platform.server.statistics;

import com.plm.platform.common.security.starter.annotation.EnablePlatformCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author crystal
 */
@SpringBootApplication
@EnablePlatformCloudResourceServer
@EnableFeignClients
@EnableTransactionManagement
@MapperScan("com.plm.platform.server.statistics.mapper")
public class PlatformServerStatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformServerStatisticsApplication.class, args);
	}

}
