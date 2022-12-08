package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.core.util.JpaUtil;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 DAO
 * @author xezzon
 */
@Repository
public class UserDAOImpl extends QuerydslJpaRepository<User, String> implements UserDAO {

  private static final QUser Q_USER_DO = QUser.user;
  @Resource
  private transient JPAQueryFactory queryFactory;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(User.class, entityManager.getMetamodel()),
        entityManager);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean update(User user) {
    JPAUpdateClause clause =
        JpaUtil.getUpdateClause(user, queryFactory, Q_USER_DO);
    clause.where(Q_USER_DO.id.eq(user.getId()));
    long affected = clause.execute();
    return affected > 0;
  }
}
