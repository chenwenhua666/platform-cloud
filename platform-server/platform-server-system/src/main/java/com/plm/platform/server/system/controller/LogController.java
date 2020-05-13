package com.plm.platform.server.system.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.QueryRequest;
import com.plm.platform.common.core.entity.constant.StringConstant;
import com.plm.platform.common.core.entity.system.Log;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.server.system.annotation.ControllerEndpoint;
import com.plm.platform.server.system.service.ILogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author crystal
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("log")
public class LogController {

    private final ILogService logService;

    @GetMapping
    public PlatformResponse logList(Log log, QueryRequest request) {
        Map<String, Object> dataTable = PlatformUtil.getDataTable(this.logService.findLogs(log, request));
        return new PlatformResponse().data(dataTable);
    }

    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('log:delete')")
    @ControllerEndpoint(exceptionMessage = "删除日志失败")
    public void deleteLogss(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] logIds = ids.split(StringConstant.COMMA);
        this.logService.deleteLogs(logIds);
    }


    @PostMapping("excel")
    @PreAuthorize("hasAuthority('log:export')")
    @ControllerEndpoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest request, Log lg, HttpServletResponse response) {
        List<Log> logs = this.logService.findLogs(lg, request).getRecords();
        ExcelKit.$Export(Log.class, response).downXlsx(logs, false);
    }
}
