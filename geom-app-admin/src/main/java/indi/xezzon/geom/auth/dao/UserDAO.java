package indi.xezzon.geom.auth.dao;

import indi.xezzon.geom.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserDAO extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {

  /**
   * 根据ID局部更新
   * @param user 更新用户基础信息，字段为null则不更新
   * @return 是否更新成功
   */
  boolean update(User user);
}
