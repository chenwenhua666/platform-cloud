package com.plm.platform.server.job.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.QueryRequest;
import com.plm.platform.common.core.entity.constant.StringConstant;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.server.job.entity.JobLog;
import com.plm.platform.server.job.service.IJobLogService;
import com.wuwenze.poi.ExcelKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author crystal
 */
@Slf4j
@Validated
@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class JobLogController {

    private final IJobLogService jobLogService;

    @GetMapping
    @PreAuthorize("hasAuthority('job:log:view')")
    public PlatformResponse jobLogList(QueryRequest request, JobLog log) {
        Map<String, Object> dataTable = PlatformUtil.getDataTable(this.jobLogService.findJobLogs(request, log));
        return new PlatformResponse().data(dataTable);
    }

    @DeleteMapping("{jobIds}")
    @PreAuthorize("hasAuthority('job:log:delete')")
    public void deleteJobLog(@NotBlank(message = "{required}") @PathVariable String jobIds) {
        String[] ids = jobIds.split(StringConstant.COMMA);
        this.jobLogService.deleteJobLogs(ids);
    }

    @GetMapping("excel")
    @PreAuthorize("hasAuthority('job:log:export')")
    public void export(QueryRequest request, JobLog jobLog, HttpServletResponse response) {
        List<JobLog> jobLogs = this.jobLogService.findJobLogs(request, jobLog).getRecords();
        ExcelKit.$Export(JobLog.class, response).downXlsx(jobLogs, false);
    }
}
