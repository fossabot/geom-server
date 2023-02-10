package indi.xezzon.geom.auth.dao.wrapper;

import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.tao.jpa.JpaWrapper;

public interface WrappedUserGroupDAO extends JpaWrapper<UserGroup, UserGroupDAO> {

}
