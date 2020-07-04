package com.plm.platform.server.statistics.controller;

import com.plm.platform.server.statistics.service.MailService;
import com.plm.platform.server.statistics.vo.MailSendVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * @author : crystal
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("send")
    @PreAuthorize("hasAuthority('mail:send')")
    public void sendHtmlEmail(@Valid MailSendVO mailSendVO) {
        mailService.sendHtmlEmail(mailSendVO);
    }

    @GetMapping("template")
    public void sentTemplateEmail(@Email(message = "{email}") String address) {
        mailService.sendTemplateEmail(address);
    }
}
