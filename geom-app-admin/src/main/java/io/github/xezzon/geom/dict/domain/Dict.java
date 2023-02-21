package io.github.xezzon.geom.dict.domain;

import static io.github.xezzon.geom.dict.domain.Dict.CODE;
import static io.github.xezzon.geom.dict.domain.Dict.TAG;

import io.github.xezzon.geom.core.constant.DatabaseConstant;
import io.github.xezzon.geom.core.manager.HibernateIdGenerator;
import io.github.xezzon.tao.dict.IDict;
import io.github.xezzon.tao.domain.TreeNode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Table(
    name = "system_dict",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {TAG, CODE})
    }
)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Dict implements IDict, TreeNode<Dict, String> {

  static final String TAG = "tag";
  static final String CODE = "code";

  @Id
  @Column(name = "id", nullable = false, updatable = false, length = DatabaseConstant.ID_LENGTH)
  @GenericGenerator(
      name = HibernateIdGenerator.GENERATOR_NAME,
      strategy = HibernateIdGenerator.GENERATOR_STRATEGY
  )
  @GeneratedValue(generator = HibernateIdGenerator.GENERATOR_NAME)
  private String id;
  /**
   * 字典目
   */
  @Column(name = TAG, nullable = false, updatable = false)
  @NotNull(message = "字典目不能为空")
  private String tag;
  /**
   * 字典值
   */
  @NotNull(message = "字典编码不能为空")
  @Column(name = CODE, nullable = false)
  private String code;
  /**
   * 字典描述
   */
  @Column(name = "label")
  private String label;
  /**
   * 排序号
   */
  @Column(name = "ordinal", nullable = false)
  private Integer ordinal;
  /**
   * 上级字典ID
   */
  @Column(name = "parent_id", nullable = false)
  private String parentId;
  @Transient
  private List<Dict> children;

  public int getOrdinal() {
    return Optional.ofNullable(ordinal).orElse(0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Dict dict = (Dict) o;
    return Objects.equals(id, dict.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
