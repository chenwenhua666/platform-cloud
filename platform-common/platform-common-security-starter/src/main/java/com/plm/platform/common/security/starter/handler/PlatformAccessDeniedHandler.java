package com.plm.platform.common.security.starter.handler;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.utils.PlatformUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author crystal
 */
public class PlatformAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        PlatformResponse platformResponse = new PlatformResponse();
        PlatformUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_VALUE,
                HttpServletResponse.SC_FORBIDDEN, platformResponse.message("没有权限访问该资源"));
    }
}
