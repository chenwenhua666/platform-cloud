package com.plm.platform.server.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author crystal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailValidationVO {

    @Email(message = "{email}")
    private String email;

    @NotBlank(message = "{required}")
    private String code;
}
