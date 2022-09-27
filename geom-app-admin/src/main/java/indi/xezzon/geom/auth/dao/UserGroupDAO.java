package indi.xezzon.geom.auth.dao;

import indi.xezzon.geom.auth.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserGroupDAO extends JpaRepository<UserGroup, String>, QuerydslPredicateExecutor<UserGroup> {

  /**
   * 局部更新用户组信息
   * @param userGroup 用户组信息 为null的字段不会更新
   * @return 是否更新成功
   */
  boolean update(UserGroup userGroup);
}
