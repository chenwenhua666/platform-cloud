package com.plm.platform.server.foundation.service.impl;

import com.plm.platform.common.core.entity.system.TradeLog;
import com.plm.platform.server.foundation.feign.IRemoteTradeLogService;
import com.plm.platform.server.foundation.mapper.TradeLogMapper;
import com.plm.platform.server.foundation.service.ITradeLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author crystal
 */
@Slf4j
@Service("tradeLogService")
@RequiredArgsConstructor
public class TradeLogServiceImpl extends ServiceImpl<TradeLogMapper, TradeLog> implements ITradeLogService {

    private final IRemoteTradeLogService remoteTradeLogService;

    @Override
    @LcnTransaction
    public void orderAndPay(TradeLog tradeLog) {
        tradeLog.setCreateTime(new Date());
        tradeLog.setStatus("下单并支付成功");

        // 保存支付日志
        this.save(tradeLog);
        log.info("用户已经下单并支付成功商品ID为{}，名称为{}的商品", tradeLog.getGoodsId(), tradeLog.getGoodsName());
        // 调用远程方法，打包并配送商品
        remoteTradeLogService.packageAndSend(tradeLog);
        // throw new RuntimeException("抛个异常，测试全局回滚");
    }
}
