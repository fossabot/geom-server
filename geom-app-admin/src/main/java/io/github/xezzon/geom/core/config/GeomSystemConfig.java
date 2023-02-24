package io.github.xezzon.geom.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xezzon
 */
@Configuration
@ConfigurationProperties(prefix = "geom.system")
@Getter
@Setter
public class GeomSystemConfig {

  private String idGenerator;
  private String saTokenDao;
}
