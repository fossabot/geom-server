package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.UserGroup;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public class UserGroupDAOImpl
    extends QuerydslJpaRepository<UserGroup, String>
    implements UserGroupDAO {

  private static final QUserGroup Q_USER_GROUP_DO = QUserGroup.userGroup;
  @Resource
  private JPAQueryFactory queryFactory;

  @Autowired
  public UserGroupDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(UserGroup.class, entityManager.getMetamodel()),
        entityManager);
  }
}
