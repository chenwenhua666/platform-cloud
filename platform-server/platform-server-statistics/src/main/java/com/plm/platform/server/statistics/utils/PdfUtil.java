package com.plm.platform.server.statistics.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.plm.platform.common.core.entity.constant.CustomConstant;
import com.plm.platform.common.core.entity.constant.PlatformConstant;
import com.plm.platform.common.core.entity.constant.StringConstant;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author crystal
 */
public class PdfUtil {

    /**
     * 通过html生成文件
     *
     * @param htmlContent html格式内容
     * @param file        输出文件file
     */
    public static void createdPdfByHtml(String htmlContent, File file) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        PdfWriter writer = null;
        try {
            // 1. 获取生成pdf的html内容
            inputStream = new ByteArrayInputStream(htmlContent.getBytes(PlatformConstant.UTF8));
            outputStream = new FileOutputStream(file);
            Document document = new Document();
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            // 2. 添加字体
            XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
            fontImp.register(getFontPath());
            // 3. 设置编码
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream,
                    Charset.forName(PlatformConstant.UTF8), fontImp);
            // 4. 关闭,(不关闭则会生成无效pdf)
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String getFontPath() {
        return getRootPath() + CustomConstant.FONT_PATH;
    }

    /**
     * 应用场景:
     * 1.在windows下,使用Thread.currentThread()获取路径时,出现空对象，导致不能使用
     * 2.在linux下,使用PdfUtils.class获取路径为null,
     *
     * @return resources路径
     */
    private static String getRootPath() {
        String path = StringConstant.EMPTY;
        // 1. linux获取resources路径
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = (classLoader == null) ? null : classLoader.getResource(CustomConstant.ROOT_PATH);
        String threadCurrentPath = (url == null) ? StringConstant.EMPTY : url.getPath();
        // 2. 如果线程获取为null,则使用当前PdfUtils.class加载路径
        if (threadCurrentPath == null || StringConstant.EMPTY.equals(threadCurrentPath)) {
            path = PdfUtil.class.getClass().getResource(CustomConstant.ROOT_PATH).getPath();
        }
        return path;
    }
}
