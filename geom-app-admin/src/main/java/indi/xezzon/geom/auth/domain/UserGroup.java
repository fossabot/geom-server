package indi.xezzon.geom.auth.domain;

import indi.xezzon.geom.core.manager.HibernateIdGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户组
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "system_user_group")
public class UserGroup {

  /**
   * 用户组主键
   */
  @Id
  @Column(name = "id", nullable = false, updatable = false, length = 64)
  @GenericGenerator(
      name = HibernateIdGenerator.GENERATOR_NAME,
      strategy = HibernateIdGenerator.GENERATOR_STRATEGY
  )
  @GeneratedValue(generator = HibernateIdGenerator.GENERATOR_NAME)
  private String id;
  /**
   * 用户组编码
   */
  @Column(name = "code", nullable = false, unique = true, length = 32)
  private String code;
  /**
   * 用户组名称
   */
  @Column(name = "name", nullable = false)
  private String name;
  /**
   * 所属用户ID
   */
  @Column(name = "owner_id", nullable = false, length = 32)
  private String ownerId;
}
