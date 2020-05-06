package com.plm.platform.server.system.mapper;

import com.plm.platform.common.core.entity.system.DataPermissionTest;
import com.plm.platform.common.datasource.starter.annotation.DataPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author crystal
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
