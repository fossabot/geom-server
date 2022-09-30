package indi.xezzon.geom.auth.dao;

import com.querydsl.core.types.Predicate;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.domain.UserGroupMember;
import java.util.List;
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

  /**
   * 查询用户组列表
   * @param userId 用户ID
   * @return 该用户所在的用户组
   */
  List<UserGroup> findAllUserGroupByUserId(String userId);
}
