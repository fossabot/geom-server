package io.github.xezzon.geom.auth.dao.impl;

import io.github.xezzon.geom.auth.dao.UserGroupDAO;
import io.github.xezzon.geom.auth.dao.wrapper.WrappedUserGroupDAO;
import io.github.xezzon.geom.auth.domain.QUserGroup;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import org.springframework.stereotype.Repository;

@Repository
public class WrappedUserGroupDAOImpl
    extends BaseJpaWrapper<UserGroup, QUserGroup, UserGroupDAO>
    implements WrappedUserGroupDAO {

  protected WrappedUserGroupDAOImpl(UserGroupDAO dao) {
    super(dao);
  }

  @Override
  protected QUserGroup getQuery() {
    return QUserGroup.userGroup;
  }

  @Override
  protected Class<UserGroup> getBeanClass() {
    return UserGroup.class;
  }
}
