package com.plm.platform.server.statistics.service.impl;

import com.plm.platform.common.core.entity.constant.CustomConstant;
import com.plm.platform.common.core.utils.DateUtil;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.redis.service.RedisService;
import com.plm.platform.server.statistics.properties.PlatformServerStatisticsProperties;
import com.plm.platform.server.statistics.service.MailService;
import com.plm.platform.server.statistics.vo.MailSendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

/**
 * @author crystal
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender jms;
    private final RedisService redisService;
    private final PlatformServerStatisticsProperties properties;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendHtmlEmail(MailSendVO mailSendVO) {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            int size = mailSendVO.getReceiver().size();
            helper.setFrom(from);
            helper.setTo(mailSendVO.getReceiver().toArray(new String[size]));
            helper.setSubject(mailSendVO.getTitle());
            helper.setText(mailSendVO.getContent(), true);
            jms.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendTemplateEmail(String address) {
        String redisKey = CustomConstant.EMAIL_CODE + address;
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(address);
            helper.setSubject(CustomConstant.EMAIL_SUBJECT);
            Context context = new Context();
            Object oldCode = redisService.get(redisKey);
            if (oldCode == null) {
                String code = RandomStringUtils.randomNumeric(properties.getEmail().getRandom());
                redisService.set(redisKey, code, properties.getEmail().getExpire());
                context.setVariable("code", code);
            } else {
                context.setVariable("code", oldCode);
            }
            context.setVariable("user", PlatformUtil.getCurrentUsername());
            context.setVariable("time", DateUtil.formatSplitTime(LocalDateTime.now()));
            String template = templateEngine.process(CustomConstant.EMAIL_TEMPLATE, context);
            helper.setText(template, true);
            jms.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }
}
