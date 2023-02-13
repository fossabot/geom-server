package io.github.xezzon.geom.auth.dao;

import io.github.xezzon.geom.auth.domain.UserGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @author xezzon
 */
public interface UserGroupMemberDAO
    extends JpaRepository<UserGroupMember, String>,
    QuerydslPredicateExecutor<UserGroupMember> {

}
