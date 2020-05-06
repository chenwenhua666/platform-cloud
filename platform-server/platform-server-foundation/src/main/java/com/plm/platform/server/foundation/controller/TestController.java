package com.plm.platform.server.foundation.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.QueryRequest;
import com.plm.platform.common.core.entity.system.SystemUser;
import com.plm.platform.common.core.entity.system.TradeLog;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.server.foundation.feign.IRemoteUserService;
import com.plm.platform.server.foundation.service.ITradeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author crystal
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final IRemoteUserService remoteUserService;
    private final ITradeLogService tradeLogService;

    /**
     * 用于演示 Feign调用受保护的远程方法
     */
    @GetMapping("user/list")
    public PlatformResponse getRemoteUserList(QueryRequest request, SystemUser user) {
        return remoteUserService.userList(request, user);
    }

    /**
     * 测试分布式事务
     */
    @GetMapping("pay")
    public void orderAndPay(TradeLog tradeLog) {
        this.tradeLogService.orderAndPay(tradeLog);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("user")
    public Map<String, Object> currentUser() {
        Map<String, Object> map = new HashMap<>(5);
        map.put("currentUser", PlatformUtil.getCurrentUser());
        map.put("currentUsername", PlatformUtil.getCurrentUsername());
        map.put("currentUserAuthority", PlatformUtil.getCurrentUserAuthority());
        map.put("currentTokenValue", PlatformUtil.getCurrentTokenValue());
        map.put("currentRequestIpAddress", PlatformUtil.getHttpServletRequestIpAddress());
        return map;
    }
}
