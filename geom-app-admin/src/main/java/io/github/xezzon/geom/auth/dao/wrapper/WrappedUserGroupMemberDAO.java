package io.github.xezzon.geom.auth.dao.wrapper;

import com.querydsl.core.types.Predicate;
import io.github.xezzon.geom.auth.dao.UserGroupMemberDAO;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.geom.auth.domain.UserGroupMember;
import io.github.xezzon.tao.jpa.JpaWrapper;
import java.util.List;

public interface WrappedUserGroupMemberDAO
    extends JpaWrapper<UserGroupMember, UserGroupMemberDAO> {

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
