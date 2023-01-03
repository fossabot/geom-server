package indi.xezzon.geom.auth.dao;

import indi.xezzon.geom.auth.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserGroupDAO
    extends JpaRepository<UserGroup, String>,
    QuerydslPredicateExecutor<UserGroup> {

}
