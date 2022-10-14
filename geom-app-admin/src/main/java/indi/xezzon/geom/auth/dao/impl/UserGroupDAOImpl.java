package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.core.util.JpaUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xezzon
 */
@Repository
public class UserGroupDAOImpl
    extends QuerydslJpaRepository<UserGroup, String>
    implements UserGroupDAO {

  private static final QUserGroup Q_USER_GROUP_DO = QUserGroup.userGroup;
  @Resource
  private transient JPAQueryFactory queryFactory;

  @Autowired
  public UserGroupDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(UserGroup.class, entityManager.getMetamodel()),
        entityManager);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean update(UserGroup userGroup) {
    JPAUpdateClause clause =
        JpaUtil.getUpdateClause(userGroup, queryFactory.update(Q_USER_GROUP_DO), Q_USER_GROUP_DO);
    clause.where(Q_USER_GROUP_DO.id.eq(userGroup.getId()));
    long affected = clause.execute();
    return affected > 0;
  }
}
