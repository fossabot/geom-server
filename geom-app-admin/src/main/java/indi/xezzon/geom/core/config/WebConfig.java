package indi.xezzon.geom.core.config;

import indi.xezzon.tao.logger.LogInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xezzon
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(@NotNull InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor());
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
