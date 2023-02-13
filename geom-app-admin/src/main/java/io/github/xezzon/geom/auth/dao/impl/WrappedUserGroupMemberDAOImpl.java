package io.github.xezzon.geom.auth.dao.impl;

import com.querydsl.core.types.Predicate;
import io.github.xezzon.geom.auth.dao.UserGroupMemberDAO;
import io.github.xezzon.geom.auth.dao.wrapper.WrappedUserGroupMemberDAO;
import io.github.xezzon.geom.auth.domain.QUserGroup;
import io.github.xezzon.geom.auth.domain.QUserGroupMember;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.geom.auth.domain.UserGroupMember;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class WrappedUserGroupMemberDAOImpl
    extends BaseJpaWrapper<UserGroupMember, QUserGroupMember, UserGroupMemberDAO>
    implements WrappedUserGroupMemberDAO {

  protected WrappedUserGroupMemberDAOImpl(UserGroupMemberDAO dao) {
    super(dao);
  }

  @Override
  protected QUserGroupMember getQuery() {
    return QUserGroupMember.userGroupMember;
  }

  @Override
  protected Class<UserGroupMember> getBeanClass() {
    return UserGroupMember.class;
  }

  @Override
  public long delete(Predicate predicate) {
    return queryFactory.delete(this.getQuery())
        .where(predicate)
        .execute();
  }

  @Override
  public List<UserGroup> findAllUserGroupByUserId(String userId) {
    final QUserGroup qUserGroup = QUserGroup.userGroup;
    final QUserGroupMember qUserGroupMember = this.getQuery();
    return queryFactory.selectFrom(qUserGroup)
        .innerJoin(qUserGroupMember).on(qUserGroup.id.eq(qUserGroupMember.groupId))
        .where(qUserGroupMember.userId.eq(userId))
        .fetch();
  }
}
