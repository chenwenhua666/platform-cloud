package com.plm.platform.server.statistics.properties;

import lombok.Data;

/**
 * @author crystal
 */
@Data
public class PlatformEmailValidationProperties {

    /**
     * 随机位数
     */
    private Integer random = 6;

    /**
     * 过期时间(秒)
     */
    private Long expire = 300L;
}
