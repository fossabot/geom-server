package io.github.xezzon.geom.open.domain;

import io.github.xezzon.geom.core.constant.DatabaseConstant;
import io.github.xezzon.geom.core.manager.HibernateIdGenerator;
import io.github.xezzon.geom.open.constant.PublicApiTypeEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(
    name = "system_public_api",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code", "owner_id"})
    }
)
public class PublicApi {

  /**
   * 主键
   */
  @Id
  @Column(name = "id", updatable = false, length = DatabaseConstant.ID_LENGTH)
  @GenericGenerator(
      name = HibernateIdGenerator.GENERATOR_NAME,
      strategy = HibernateIdGenerator.GENERATOR_STRATEGY
  )
  @GeneratedValue(generator = HibernateIdGenerator.GENERATOR_NAME)
  private String id;
  /**
   * 调用识别码
   */
  @Column(name = "code", nullable = false)
  private String code;
  /**
   * 服务提供方
   */
  @Column(name = "service")
  private String service;
  /**
   * 接口名称
   */
  @Column(name = "name", nullable = false)
  private String name;
  /**
   * 类型
   */
  @Column(
      name = "type",
      nullable = false,
      updatable = false,
      length = DatabaseConstant.ENUM_LENGTH
  )
  @Enumerated(EnumType.STRING)
  private PublicApiTypeEnum type;
  /**
   * 是否启用
   */
  @Column(name = "enabled", nullable = false)
  private Boolean enabled;
  /**
   * 所属用户组主键
   */
  @Column(
      name = "owner_id",
      nullable = false,
      updatable = false,
      length = DatabaseConstant.ID_LENGTH
  )
  private String ownerId;
}
