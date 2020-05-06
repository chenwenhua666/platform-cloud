package com.plm.platform.common.security.starter.annotation;

import com.plm.platform.common.security.starter.configure.PlatformCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author crystal
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(PlatformCloudResourceServerConfigure.class)
public @interface EnablePlatformCloudResourceServer {
}
