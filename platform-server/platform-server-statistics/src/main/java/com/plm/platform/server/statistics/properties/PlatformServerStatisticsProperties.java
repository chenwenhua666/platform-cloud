package com.plm.platform.server.statistics.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author crystal
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:platform-server-statistics.properties"})
@ConfigurationProperties(prefix = "platform.server.statistics")
public class PlatformServerStatisticsProperties {
    private PlatformEmailValidationProperties email = new PlatformEmailValidationProperties();
    private String title;
}
