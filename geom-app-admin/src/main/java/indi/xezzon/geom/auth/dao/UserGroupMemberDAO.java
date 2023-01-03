package indi.xezzon.geom.auth.dao;

import indi.xezzon.geom.auth.domain.UserGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserGroupMemberDAO
    extends JpaRepository<UserGroupMember, String>,
    QuerydslPredicateExecutor<UserGroupMember> {

}
