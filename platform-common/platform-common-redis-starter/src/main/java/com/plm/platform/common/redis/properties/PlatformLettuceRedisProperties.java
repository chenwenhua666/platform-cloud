package com.plm.platform.common.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author crystal
 */
@ConfigurationProperties(prefix = "platform.lettuce.redis")
public class PlatformLettuceRedisProperties {

    /**
     * 是否开启Lettuce Redis
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "PlatformLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
