package com.plm.platform.server.statistics.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.plm.platform.common.core.entity.constant.StringConstant;
import com.plm.platform.common.core.exception.PlatformException;
import com.plm.platform.server.statistics.service.SqlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author crystal
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class SqlServiceImpl implements SqlService {
    private final JdbcTemplate jdbcTemplate;

    @DS("#ds")
    @Override
    public List select(String sql, String ds) throws PlatformException {
        try {
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            log.info("查询结果出错，error={}", e);
            throw new PlatformException("SQL语句错误");
        }
    }

    @DS("#ds")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchUpdate(String sql, String ds) throws PlatformException {
        try {
            jdbcTemplate.batchUpdate(StringUtils.split(sql, StringConstant.SEMICOLON));
        } catch (Exception e) {
            log.info("SQL执行出错,error={}", e);
            throw new PlatformException("SQL语句错误");
        }
    }
}
