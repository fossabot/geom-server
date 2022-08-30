package indi.xezzon.geom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author xezzon
 */
@SpringBootApplication
@EnableJpaAuditing
public class DbSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(DbSystemApplication.class, args);
  }
}
