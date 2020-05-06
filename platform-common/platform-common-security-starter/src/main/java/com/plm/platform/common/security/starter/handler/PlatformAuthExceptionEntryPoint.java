package com.plm.platform.common.security.starter.handler;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.utils.PlatformUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author crystal
 */
@Slf4j
public class PlatformAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        PlatformUtil.makeResponse(response, MediaType.APPLICATION_JSON_VALUE, status, new PlatformResponse().message(message));
    }
}
