package indi.xezzon.geom.auth.dao;

import com.querydsl.core.types.Predicate;
import indi.xezzon.geom.auth.domain.UserGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserGroupMemberDAO
    extends JpaRepository<UserGroupMember, String>,
    QuerydslPredicateExecutor<UserGroupMember> {

  /**
   * 按条件移除
   * @param predicate 查询条件
   * @return 影响记录数
   */
  long delete(Predicate predicate);
}
