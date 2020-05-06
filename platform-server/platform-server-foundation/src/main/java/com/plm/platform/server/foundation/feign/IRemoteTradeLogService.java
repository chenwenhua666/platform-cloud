package com.plm.platform.server.foundation.feign;

import com.plm.platform.common.core.entity.constant.PlatformServerConstant;
import com.plm.platform.common.core.entity.system.TradeLog;
import com.plm.platform.server.foundation.feign.fallback.RemoteTradeLogServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author crystal
 */
@FeignClient(value = PlatformServerConstant.PLATFORM_SERVER_SYSTEM, contextId = "tradeLogServiceClient", fallbackFactory = RemoteTradeLogServiceFallback.class)
public interface IRemoteTradeLogService {

    /**
     * 打包派送
     *
     * @param tradeLog 交易日志
     */
    @PostMapping("package/send")
    void packageAndSend(@RequestBody TradeLog tradeLog);
}
