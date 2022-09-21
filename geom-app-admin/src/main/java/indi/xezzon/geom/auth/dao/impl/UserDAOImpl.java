package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户 DAO
 * @author xezzon
 */
@Repository
public class UserDAOImpl extends QuerydslJpaRepository<User, String> implements UserDAO {

  private static final QUser Q_USER_DO = QUser.user;
  @Resource
  private JPAQueryFactory queryFactory;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(User.class, entityManager.getMetamodel()),
        entityManager);
  }
}
