package com.plm.platform.server.statistics.controller;

import com.plm.platform.common.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : cwh
 * 2020/5/12 0012
 * description ：
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final RedisService redisService;

    @GetMapping("test")
    public String hello(){
        boolean flag = redisService.hasKey("access_token");
        if (flag) {
            return "no such key";
        }
        return "platform-server-statistics ;)";
    }
}
