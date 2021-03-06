package com.plm.platform.server.generator.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.QueryRequest;
import com.plm.platform.common.core.entity.constant.GeneratorConstant;
import com.plm.platform.common.core.entity.system.Column;
import com.plm.platform.common.core.entity.system.GeneratorConfig;
import com.plm.platform.common.core.exception.PlatformException;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.core.utils.FileUtil;
import com.plm.platform.server.generator.helper.GeneratorHelper;
import com.plm.platform.server.generator.service.IGeneratorConfigService;
import com.plm.platform.server.generator.service.IGeneratorService;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileSystemUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
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
public class GeneratorController {

    private static final String SUFFIX = "_code.zip";

    private final IGeneratorService generatorService;
    private final IGeneratorConfigService generatorConfigService;
    private final GeneratorHelper generatorHelper;
    private final DynamicDataSourceProperties properties;

    @GetMapping("datasources")
    @PreAuthorize("hasAuthority('gen:generate')")
    public PlatformResponse datasources() {
        Map<String, DataSourceProperty> datasources = properties.getDatasource();
        List<String> datasourcesName = new ArrayList<>();
        datasources.forEach((k, v) -> {
            String datasourceName = StringUtils.substringBefore(StringUtils.substringAfterLast(v.getUrl(), "/"), "?");
            datasourcesName.add(datasourceName);
        });
        return new PlatformResponse().data(datasourcesName);
    }

    @GetMapping("tables")
    @PreAuthorize("hasAuthority('gen:generate')")
    public PlatformResponse tablesInfo(String tableName, String datasource, QueryRequest request) {
        Map<String, Object> dataTable = PlatformUtil.getDataTable(generatorService.getTables(tableName, request, GeneratorConstant.DATABASE_TYPE, datasource));
        return new PlatformResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('gen:generate:gen')")
    public void generate(@NotBlank(message = "{required}") String name,
                         @NotBlank(message = "{required}") String datasource,
                         String remark, HttpServletResponse response) throws Exception {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        if (generatorConfig == null) {
            throw new PlatformException("代码生成配置为空");
        }

        String className = name;
        if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(name, generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }

        generatorConfig.setTableName(name);
        generatorConfig.setClassName(PlatformUtil.underscoreToCamel(className));
        generatorConfig.setTableComment(remark);
        // 生成代码到临时目录
        List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_TYPE, datasource, name);
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, name + SUFFIX, true, response);
        // 删除临时目录
        FileSystemUtils.deleteRecursively(new File(GeneratorConstant.TEMP_PATH));
    }
}
