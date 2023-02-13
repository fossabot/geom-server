package io.github.xezzon.geom.auth.dao.impl;

import io.github.xezzon.geom.auth.dao.UserDAO;
import io.github.xezzon.geom.auth.dao.wrapper.WrappedUserDAO;
import io.github.xezzon.geom.auth.domain.QUser;
import io.github.xezzon.geom.auth.domain.User;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
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
