package com.plm.platform.common.core.entity.constant;

/**
 * @author crystal
 */
public interface CustomConstant {

    /**
     * 邮箱验证码前缀
     */
    String EMAIL_CODE = "platform_email_code_";

    /**
     * 生成文件的临时目录
     */
    String FILE_PATH = "plm_file_temp/";

    /**
     * freemarker获取pdf模板名称
     */
    String PDF_TEMPLATE = "reportTemplate.html";

    /**
     * thymeleaf获取发送邮件模板名称
     */
    String EMAIL_TEMPLATE = "emailTemplate";

    /**
     * 模板发送验证邮件主题
     */
    String EMAIL_SUBJECT = "PLM邮箱验证";

    /**
     * resources目录根路径
     */
    String ROOT_PATH = "/";

    /**
     * pdf中文字体路径
     */
    String FONT_PATH = "/fonts/SIMKAI.TTF";
}
