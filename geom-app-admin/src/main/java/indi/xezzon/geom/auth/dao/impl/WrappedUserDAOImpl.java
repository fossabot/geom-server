package indi.xezzon.geom.auth.dao.impl;

import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.dao.wrapper.WrappedUserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.tao.jpa.BaseJpaWrapper;
import org.springframework.stereotype.Repository;

@Repository
public class WrappedUserDAOImpl
    extends BaseJpaWrapper<User, QUser, UserDAO>
    implements WrappedUserDAO {

  protected WrappedUserDAOImpl(UserDAO dao) {
    super(dao);
  }

  @Override
  protected QUser getQuery() {
    return QUser.user;
  }

  @Override
  protected Class<User> getBeanClass() {
    return User.class;
  }
}
