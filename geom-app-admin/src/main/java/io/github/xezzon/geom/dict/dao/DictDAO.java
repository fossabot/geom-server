package io.github.xezzon.geom.dict.dao;


import io.github.xezzon.geom.dict.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface DictDAO extends JpaRepository<Dict, Object>, QuerydslPredicateExecutor<Dict> {

}
