package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.auth.dao.UserGroupMemberDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.QUserGroupMember;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.domain.UserGroupMember;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xezzon
 */
public class UserGroupMemberDAOImpl
    extends QuerydslJpaRepository<UserGroupMember, String>
    implements UserGroupMemberDAO {

  private static final QUserGroupMember Q_USER_GROUP_MEMBER = QUserGroupMember.userGroupMember;
  @Resource
  private transient JPAQueryFactory queryFactory;

  @Autowired
  public UserGroupMemberDAOImpl(EntityManager entityManager) {
    super(
        new JpaMetamodelEntityInformation<>(
            UserGroupMember.class, entityManager.getMetamodel()
        ),
        entityManager
    );
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public long delete(Predicate predicate) {
    return queryFactory.delete(Q_USER_GROUP_MEMBER)
        .where(predicate)
        .execute();
  }

  @Override
  public List<UserGroup> findAllUserGroupByUserId(String userId) {
    final QUserGroup qUserGroup = QUserGroup.userGroup;
    return queryFactory.selectFrom(qUserGroup)
        .innerJoin(Q_USER_GROUP_MEMBER).on(qUserGroup.id.eq(Q_USER_GROUP_MEMBER.groupId))
        .where(Q_USER_GROUP_MEMBER.userId.eq(userId))
        .fetch();
  }
}
