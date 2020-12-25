package com.plm.platform.server.statistics.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.constant.CustomConstant;
import com.plm.platform.common.core.entity.constant.StringConstant;
import com.plm.platform.common.core.exception.PlatformException;
import com.plm.platform.common.core.utils.FileUtil;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.redis.service.RedisService;
import com.plm.platform.server.statistics.service.ReportService;
import com.plm.platform.server.statistics.utils.TemplateUtil;
import com.plm.platform.server.statistics.vo.MailValidationVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author crystal
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ReportServiceImpl implements ReportService {
    private static final String SUFFIX = "_report.pdf";
    private static final String DOWNLOAD_NAME = "private_report.pdf";

    private final RedisService redisService;

    @Override
    public void downloadReport(MailValidationVO mailValidationVO, HttpServletResponse response) throws Exception {
        validated(mailValidationVO);
        JSONObject data = toJsonObject(PlatformUtil.getCurrentUser());
        String prefix = System.currentTimeMillis() + StringConstant.UNDER_LINE + UUID.randomUUID().toString();
        String fileName = prefix + SUFFIX;
        File file = new File(CustomConstant.FILE_PATH + fileName);
        TemplateUtil.createFileByTemplate(CustomConstant.PDF_TEMPLATE, file, data);
        FileUtil.download(CustomConstant.FILE_PATH + fileName, DOWNLOAD_NAME, true, response);
        FileSystemUtils.deleteRecursively(new File(CustomConstant.FILE_PATH));
    }

    @Override
    public PlatformResponse viewReport(MailValidationVO mailValidationVO) throws Exception {
        validated(mailValidationVO);
        JSONObject data = toJsonObject(PlatformUtil.getCurrentUser());
        String constant = TemplateUtil.getTemplateContent(CustomConstant.PDF_TEMPLATE, data);
        PlatformResponse platformResponse = new PlatformResponse();
        return platformResponse.data(constant);
    }

    private void validated(MailValidationVO mailValidationVO) throws PlatformException {
        String key = CustomConstant.EMAIL_CODE + mailValidationVO.getEmail();
        String code = mailValidationVO.getCode();
        Object codeInRedis = redisService.get(key);
        if (StringUtils.isBlank(code)) {
            throw new PlatformException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new PlatformException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, String.valueOf(codeInRedis))) {
            throw new PlatformException("验证码不正确");
        }
    }

    private JSONObject toJsonObject(Object o) {
        return JSONObject.parseObject(JSONObject.toJSON(o).toString());
    }
}
