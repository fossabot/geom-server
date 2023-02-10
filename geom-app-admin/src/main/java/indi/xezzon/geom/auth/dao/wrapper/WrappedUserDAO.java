package indi.xezzon.geom.auth.dao.wrapper;

import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.tao.jpa.JpaWrapper;

public interface WrappedUserDAO extends JpaWrapper<User, UserDAO> {

}
