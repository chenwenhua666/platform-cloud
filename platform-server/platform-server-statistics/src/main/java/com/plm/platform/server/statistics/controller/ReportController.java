package com.plm.platform.server.statistics.controller;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.server.statistics.service.ReportService;
import com.plm.platform.server.statistics.vo.MailValidationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author : crystal
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("download")
    public void downloadReport(@Valid MailValidationVO mailValidationVO, HttpServletResponse response) throws Exception {
        reportService.downloadReport(mailValidationVO, response);
    }

    @PostMapping("view")
    public PlatformResponse downloadReport(@Valid MailValidationVO mailValidationVO) throws Exception{
        return reportService.viewReport(mailValidationVO);
    }
}
