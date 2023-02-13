package io.github.xezzon.geom.auth.dao.wrapper;

import io.github.xezzon.geom.auth.dao.UserDAO;
import io.github.xezzon.geom.auth.domain.User;
import io.github.xezzon.tao.jpa.JpaWrapper;

public interface WrappedUserDAO extends JpaWrapper<User, UserDAO> {

}
