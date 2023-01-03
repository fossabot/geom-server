package indi.xezzon.geom.auth.dao.wrapper;

import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.external.jpa.JpaWrapper;

public interface WrappedUserGroupDAO extends JpaWrapper<UserGroup, UserGroupDAO> {

}
