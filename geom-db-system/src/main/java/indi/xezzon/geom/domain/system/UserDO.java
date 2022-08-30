package indi.xezzon.geom.domain.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "system_user")
@EntityListeners(AuditingEntityListener.class)
public class UserDO {

  /**
   * 用户主键
   */
  @Id
  @Column(name = "id", nullable = false, updatable = false, length = 64)
  @GenericGenerator(name = "id-generator", strategy = "indi.xezzon.geom.manager.HibernateIdGenerator")
  @GeneratedValue(generator = "id-generator")
  private String id;

  /**
   * 用户名
   */
  @Column(name = "username", nullable = false, unique = true, length = 32)
  private String username;

  /**
   * 密码
   */
  @Column(name = "cipher", nullable = false)
  private String cipher;

  /**
   * 用户昵称
   */
  @Column(name = "nickname")
  private String nickname;

  /**
   * 激活时间
   * 该时间之前账号不可用
   */
  @Column(name = "activate_time", nullable = false)
  private LocalDateTime activateTime;

  /**
   * 记录创建时间
   */
  @CreatedDate
  @Column(name = "create_time", nullable = false, updatable = false)
  private LocalDateTime createTime;

  /**
   * 最后更新时间
   */
  @LastModifiedDate
  @Column(name = "update_time", nullable = false)
  private LocalDateTime updateTime;
}
