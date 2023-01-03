package indi.xezzon.geom.auth.dao.impl;

import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.dao.wrapper.WrappedUserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.external.jpa.BaseJpaWrapper;
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
