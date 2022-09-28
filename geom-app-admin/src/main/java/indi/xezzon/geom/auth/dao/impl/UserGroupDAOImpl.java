package indi.xezzon.geom.auth.dao.impl;

import cn.hutool.core.util.ReflectUtil;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.UserGroup;
import java.lang.reflect.Field;
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
 * @author xezzon
 */
@Repository
public class UserGroupDAOImpl
    extends QuerydslJpaRepository<UserGroup, String>
    implements UserGroupDAO {

  private static final QUserGroup Q_USER_GROUP_DO = QUserGroup.userGroup;
  @Resource
  private transient JPAQueryFactory queryFactory;

  @Autowired
  public UserGroupDAOImpl(EntityManager entityManager) {
    super(new JpaMetamodelEntityInformation<>(UserGroup.class, entityManager.getMetamodel()),
        entityManager);
  }

  @Override
  @Transactional(rollbackFor = {Exception.class})
  public boolean update(UserGroup userGroup) {
    JPAUpdateClause clause = queryFactory.update(Q_USER_GROUP_DO);
    Set<Field> fields = Arrays.stream(userGroup.getClass().getDeclaredFields())
        .filter(field -> Objects.nonNull(field.getAnnotation(Column.class)))
        .filter(field -> Objects.equals(field.getAnnotation(Column.class).updatable(), true))
        .collect(Collectors.toSet());
    if (fields.isEmpty()) {
      return false;
    }
    for (Field field : fields) {
      Path path = (Path<?>) ReflectUtil.getFieldValue(Q_USER_GROUP_DO, field.getName());
      Object value = ReflectUtil.getFieldValue(userGroup, field.getName());
      if (value != null) {
        clause.set(path, value);
      }
    }
    clause.where(Q_USER_GROUP_DO.id.eq(userGroup.getId()));
    long affected = clause.execute();
    return affected > 0;
  }
}
