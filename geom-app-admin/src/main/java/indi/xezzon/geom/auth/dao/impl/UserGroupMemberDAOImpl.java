package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.auth.dao.UserGroupMemberDAO;
import indi.xezzon.geom.auth.domain.QUserGroupMember;
import indi.xezzon.geom.auth.domain.UserGroupMember;
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
  private JPAQueryFactory queryFactory;

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
}
