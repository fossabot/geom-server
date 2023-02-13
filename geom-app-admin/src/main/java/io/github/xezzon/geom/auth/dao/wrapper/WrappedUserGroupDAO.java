package io.github.xezzon.geom.auth.dao.wrapper;

import io.github.xezzon.geom.auth.dao.UserGroupDAO;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.tao.jpa.JpaWrapper;

public interface WrappedUserGroupDAO extends JpaWrapper<UserGroup, UserGroupDAO> {

}
