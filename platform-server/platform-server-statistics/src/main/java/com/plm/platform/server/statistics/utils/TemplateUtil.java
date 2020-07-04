package com.plm.platform.server.statistics.utils;

import com.google.common.io.Files;
import com.plm.platform.common.core.entity.constant.PlatformConstant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @author crystal
 */
public class TemplateUtil {

    private static Template getTemplate(String templateName) throws Exception {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        String templatePath = TemplateUtil.class.getResource("/templates/").getPath();
        File file = new File(templatePath);
        if (!file.exists()) {
            templatePath = System.getProperties().getProperty(PlatformConstant.JAVA_TEMP_DIR);
            file = new File(templatePath + File.separator + templateName);
            FileUtils.copyInputStreamToFile(Objects.requireNonNull(TemplateUtil.class
                    .getClassLoader().getResourceAsStream("classpath:templates/" + templateName)), file);
        }
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        configuration.setDefaultEncoding(PlatformConstant.UTF8);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return configuration.getTemplate(templateName);
    }

    public static void createFileByTemplate(String templateName, File file, Object data) throws Exception {
        Template template = getTemplate(templateName);
        Files.createParentDirs(file);
        StringWriter out = new StringWriter();
        template.process(data, out);
        String content = out.toString();
        PdfUtil.createdPdfByHtml(content, file);
    }

    public static String getTemplateContent(String templateName, Object data) throws Exception {
        Template template = getTemplate(templateName);
        StringWriter out = new StringWriter();
        template.process(data, out);
        return out.toString();
    }
}
