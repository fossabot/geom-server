package indi.xezzon.geom.dao.system;

import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.system.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserDAO extends JpaRepository<UserDO, String>, QuerydslPredicateExecutor<UserDO> {

  /**
   * 保存用户
   * @param user 用户信息
   */
  void save(User user);
}
