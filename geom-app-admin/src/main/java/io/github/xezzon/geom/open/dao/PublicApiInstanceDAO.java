package io.github.xezzon.geom.open.dao;

import io.github.xezzon.geom.open.domain.PublicApiInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface PublicApiInstanceDAO
    extends JpaRepository<PublicApiInstance, String>,
    QuerydslPredicateExecutor<PublicApiInstance> {

}
