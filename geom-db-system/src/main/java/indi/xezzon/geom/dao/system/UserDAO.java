package indi.xezzon.geom.dao.system;

import indi.xezzon.geom.domain.system.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserDAO extends JpaRepository<UserDO, String>, QuerydslPredicateExecutor<UserDO> {

}
