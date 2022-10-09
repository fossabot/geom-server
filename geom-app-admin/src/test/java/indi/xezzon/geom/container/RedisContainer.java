package indi.xezzon.geom.container;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.GenericContainer;

@Component
@Order(0)
public class RedisContainer {

  private static final int PORT = 6379;
  private final GenericContainer<?> redis = new GenericContainer<>("redis:7-alpine")
      .withExposedPorts(PORT);

  @PostConstruct
  public void postConstruct() {
    redis.start();
    System.setProperty("spring.redis.host", redis.getHost());
    System.setProperty("spring.redis.port", redis.getMappedPort(PORT).toString());
  }

  @PreDestroy
  public void preDestroy() {
    redis.stop();
  }
}
