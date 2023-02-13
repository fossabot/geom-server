package io.github.xezzon.geom.open.domain;

import io.github.xezzon.geom.core.constant.DatabaseConstant;
import io.github.xezzon.geom.core.manager.HibernateIdGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

/**
 * 开放接口实例
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(
    name = "system_public_api_instance",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"api_id", "owner_id"})
    }
)
public class PublicApiInstance {

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
   * 对应的开放接口主键
   */
  @Column(
      name = "api_id",
      nullable = false,
      updatable = false,
      length = DatabaseConstant.ID_LENGTH
  )
  private String apiId;
  /**
   * 申请接口的用户组
   */
  @Column(
      name = "owner_id",
      nullable = false,
      updatable = false,
      length = DatabaseConstant.ID_LENGTH
  )
  private String ownerId;
  /**
   * 是否启用
   */
  @Column(name = "enabled", nullable = false)
  private Boolean enabled;
  /**
   * 回调地址 webhook类型的接口必填
   */
  @Column(name = "callback")
  private String callback;
  /**
   * 回调时的请求头
   */
  @Column(name = "headers", columnDefinition = "TEXT")
  private String headers;

  /**
   * 接口信息
   */
  @Transient
  private PublicApi publicApi;
}
