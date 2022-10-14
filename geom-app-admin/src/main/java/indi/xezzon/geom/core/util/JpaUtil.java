package indi.xezzon.geom.core.util;

import cn.hutool.core.util.ReflectUtil;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAUpdateClause;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author xezzon
 */
public class JpaUtil {

  /**
   * 局部更新语句
   * @param obj 更新的信息
   * @param clause update语句
   * @param dataObj DO对象
   * @return 拼接后的update语句
   */
  public static JPAUpdateClause getUpdateClause(
      Object obj,
      JPAUpdateClause clause,
      Object dataObj
  ) {
    LocalDateTime current = LocalDateTime.now();
    Set<Field> fields = Arrays.stream(obj.getClass().getDeclaredFields())
        .filter(field -> Objects.nonNull(field.getAnnotation(Column.class)))
        .filter(field -> field.getAnnotation(Column.class).updatable())
        .collect(Collectors.toSet());
    for (Field field : fields) {
      Path path = (Path<?>) ReflectUtil.getFieldValue(dataObj, field.getName());
      Object value = ReflectUtil.getFieldValue(obj, field.getName());
      if (field.isAnnotationPresent(LastModifiedDate.class)) {
        clause.set(path, current);
      }
      if (value != null) {
        clause.set(path, value);
      }
    }
    return clause;
  }
}
