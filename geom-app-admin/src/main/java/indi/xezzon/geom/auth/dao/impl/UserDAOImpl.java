package indi.xezzon.geom.auth.dao.impl;

import cn.hutool.core.util.ReflectUtil;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 DAO
 * @author xezzon
 */
@Repository
public class UserDAOImpl extends QuerydslJpaRepository<User, String> implements UserDAO {

  private static final QUser Q_USER_DO = QUser.user;
  @Resource
  private JPAQueryFactory queryFactory;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(User.class, entityManager.getMetamodel()),
        entityManager);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean update(User user) {
    JPAUpdateClause clause = queryFactory.update(Q_USER_DO);
    Set<Field> fields = Arrays.stream(user.getClass().getDeclaredFields())
        .filter(field -> Objects.nonNull(field.getAnnotation(Column.class)))
        .filter(field -> Objects.equals(field.getAnnotation(Column.class).updatable(), true))
        .collect(Collectors.toSet());
    if (fields.isEmpty()) {
      return false;
    }
    for (Field field : fields) {
      Path path = (Path<?>) ReflectUtil.getFieldValue(Q_USER_DO, field.getName());
      Object value = ReflectUtil.getFieldValue(user, field.getName());
      if (value != null) {
        clause.set(path, value);
      }
    }
    clause.set(Q_USER_DO.updateTime, LocalDateTime.now());
    clause.where(Q_USER_DO.id.eq(user.getId()));
    long affected = clause.execute();
    return affected > 0;
  }
}
