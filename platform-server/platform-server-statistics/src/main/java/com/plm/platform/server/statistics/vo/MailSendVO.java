package com.plm.platform.server.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author crystal
 * 发送邮件时，接收参数的类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSendVO {

    @NotEmpty(message = "{required}")
    private List<String> receiver;

    @NotBlank(message = "{required}")
    private String title;

    @NotBlank(message = "{required}")
    private String content;
}
