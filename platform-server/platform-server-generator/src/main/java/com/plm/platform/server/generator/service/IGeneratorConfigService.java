package com.plm.platform.server.generator.service;

import com.plm.platform.common.core.entity.system.GeneratorConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author crystal
 */
public interface IGeneratorConfigService extends IService<GeneratorConfig> {

    /**
     * 查询
     *
     * @return GeneratorConfig
     */
    GeneratorConfig findGeneratorConfig();

    /**
     * 修改
     *
     * @param generatorConfig generatorConfig
     */
    void updateGeneratorConfig(GeneratorConfig generatorConfig);

}
