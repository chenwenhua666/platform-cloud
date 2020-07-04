package com.plm.platform.server.statistics.service;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.server.statistics.vo.MailValidationVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author crystal
 */
public interface ReportService {

    /**
     * 下载报告
     *
     * @param mailValidationVO 邮箱验证VO
     * @param response         输出流
     * @throws Exception 模板异常
     */
    void downloadReport(MailValidationVO mailValidationVO, HttpServletResponse response) throws Exception;

    /**
     * 预览
     *
     * @param mailValidationVO 邮箱验证VO
     * @return 实体
     * @throws Exception 获取html和绑定数据异常
     */
    PlatformResponse viewReport(MailValidationVO mailValidationVO) throws Exception;
}
