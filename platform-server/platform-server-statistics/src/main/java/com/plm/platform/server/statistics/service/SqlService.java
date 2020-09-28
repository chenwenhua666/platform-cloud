package com.plm.platform.server.statistics.service;

import com.plm.platform.common.core.exception.PlatformException;

import java.util.List;

/**
 * @author crystal
 */
public interface SqlService {
    /**
     * sql查询
     * @param sql 执行的sql
     * @param ds 数据源
     * @return 数据集
     * @throws PlatformException 执行异常
     */
    List select(String sql, String ds) throws PlatformException;

    /**
     * 批量执行增删改
     * @param sql 执行的sql
     * @param ds 数据源
     * @throws PlatformException 执行异常
     */
    void batchUpdate(String sql, String ds) throws PlatformException;
}
