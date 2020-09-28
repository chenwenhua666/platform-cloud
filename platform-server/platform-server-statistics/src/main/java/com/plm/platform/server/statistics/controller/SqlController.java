package com.plm.platform.server.statistics.controller;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.constant.StringConstant;
import com.plm.platform.common.core.exception.PlatformException;
import com.plm.platform.server.statistics.service.SqlService;
import com.plm.platform.server.statistics.vo.DataSourceVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author crystal
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("sql")
public class SqlController {

    private final DynamicDataSourceProperties properties;
    private final SqlService sqlService;

    @GetMapping("datasource")
    public PlatformResponse getDataSources() {
        Map<String, DataSourceProperty> dataSources = properties.getDatasource();
        List<DataSourceVO> dataSourcesName = new ArrayList<>();
        dataSources.forEach((k, v) -> {
            String databaseName = StringUtils.substringBefore(StringUtils.substringAfterLast(v.getUrl(),
                    StringConstant.SLASH), StringConstant.QUESTION_MARK);
            dataSourcesName.add(new DataSourceVO(k, databaseName));
        });
        return new PlatformResponse().data(dataSourcesName);
    }

    @PostMapping("run")
    @PreAuthorize("hasAuthority('sql:run')")
    public PlatformResponse run(@NotBlank(message = "{required}") String sqlMain,
                                @NotBlank(message = "{required}") String datasource) throws PlatformException {
        PlatformResponse platformResponse = new PlatformResponse();
        if (StringUtils.containsIgnoreCase(sqlMain,StringConstant.SELECT)){
            List list = sqlService.select(sqlMain.trim(), datasource);
            return platformResponse.data(list);
        } else {
            sqlService.batchUpdate(sqlMain.trim(), datasource);
            return platformResponse.message("执行成功");
        }
    }
}
