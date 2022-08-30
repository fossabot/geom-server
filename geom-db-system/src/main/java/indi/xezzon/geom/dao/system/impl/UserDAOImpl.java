package indi.xezzon.geom.dao.system.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.dao.system.UserDAO;
import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.geom.domain.system.UserDO;
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
public class UserDAOImpl extends QuerydslJpaRepository<UserDO, String> implements UserDAO {
  private static final QUserDO Q_USER_DO = QUserDO.userDO;
  @Resource
  private JPAQueryFactory queryFactory;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(UserDO.class, entityManager.getMetamodel()), entityManager);
  }
}
