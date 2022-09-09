package indi.xezzon.geom.auth.dao.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUserDO;
import indi.xezzon.geom.auth.domain.UserDO;
import indi.xezzon.geom.auth.domain.convert.UserConvert;
import indi.xezzon.geom.domain.User;
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
public class UserDAOImpl extends QuerydslJpaRepository<UserDO, String> implements UserDAO {

  private static final QUserDO Q_USER_DO = QUserDO.userDO;
  @Resource
  private JPAQueryFactory queryFactory;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(UserDO.class, entityManager.getMetamodel()),
        entityManager);
  }

  @Override
  @Transactional
  public void save(User user) {
    UserDO userDO = UserConvert.INSTANCE.into(user);
    super.save(userDO);
    user.setId(userDO.getId());
  }
}
