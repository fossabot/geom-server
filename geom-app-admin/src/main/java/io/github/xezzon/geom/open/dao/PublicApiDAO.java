package io.github.xezzon.geom.open.dao;

import io.github.xezzon.geom.open.domain.PublicApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface PublicApiDAO
    extends JpaRepository<PublicApi, String>,
    QuerydslPredicateExecutor<PublicApi> {

}
