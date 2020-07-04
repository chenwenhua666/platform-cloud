package com.plm.platform.server.statistics.service;

import com.plm.platform.server.statistics.vo.MailSendVO;

/**
 * @author crystal
 */
public interface MailService {

    /**
     * 邮件发送
     *
     * @param mailSendVO 邮件VO
     */
    void sendHtmlEmail(MailSendVO mailSendVO);

    /**
     * 发送模板邮件
     *
     * @param address 收件地址
     */
    void sendTemplateEmail(String address);
}
