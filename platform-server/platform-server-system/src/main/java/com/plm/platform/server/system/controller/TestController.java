package com.plm.platform.server.system.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.system.TradeLog;
import com.plm.platform.server.system.service.ITradeLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author crystal
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ITradeLogService tradeLogService;

    @PostMapping("package/send")
    public void packageSend(@RequestBody TradeLog tradeLog) {
        this.tradeLogService.packageAndSend(tradeLog);
    }

    @GetMapping("scope/test")
    @PreAuthorize("#oauth2.hasScope('write')")
    public PlatformResponse testScope() {
        return new PlatformResponse().message("当前client包含write scope");
    }
}
