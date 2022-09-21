package indi.xezzon.geom.auth.dao;

import indi.xezzon.geom.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserDAO extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {
}
