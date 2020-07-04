package com.plm.platform.server.statistics.handler;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.handler.BaseExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author crystal
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {

    @ExceptionHandler(value = MailSendException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PlatformResponse handleMailSendException(MailSendException e) {
        String errorMessage = "Invalid Addresses";
        log.error("邮件发送异常，异常信息", e);
        PlatformResponse response =  new PlatformResponse();
        if (StringUtils.containsIgnoreCase(e.getMessage(), errorMessage)) {
            return response.message("无效的邮件地址");
        } else {
            return response.message(e.getMessage());
        }
    }
}
