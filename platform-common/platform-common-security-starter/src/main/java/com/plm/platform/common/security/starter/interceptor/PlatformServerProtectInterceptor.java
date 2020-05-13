package com.plm.platform.common.security.starter.interceptor;

import com.plm.platform.common.core.entity.PlatformResponse;
import com.plm.platform.common.core.entity.constant.PlatformConstant;
import com.plm.platform.common.core.utils.PlatformUtil;
import com.plm.platform.common.security.starter.properties.PlatformCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author crystal
 */
public class PlatformServerProtectInterceptor implements HandlerInterceptor {

    private PlatformCloudSecurityProperties properties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws IOException {
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        // 从请求头中获取 Gateway Token
        String token = request.getHeader(PlatformConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(PlatformConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 Gateway Token的正确性
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            PlatformResponse platformResponse = new PlatformResponse();
            PlatformUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, platformResponse.message("请通过网关获取资源"));
            return false;
        }
    }

    public void setProperties(PlatformCloudSecurityProperties properties) {
        this.properties = properties;
    }
}
