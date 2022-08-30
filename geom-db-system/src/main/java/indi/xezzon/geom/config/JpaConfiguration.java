package indi.xezzon.geom.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xezzon
 */
@Configuration
public class JpaConfiguration {
  @Bean
  public JPAQueryFactory queryFactory(EntityManager em) {
    return new JPAQueryFactory(em);
  }
}
