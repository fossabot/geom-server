package indi.xezzon.geom.auth.domain;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.core.manager.HibernateIdGenerator;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class User {

  /**
   * 用户主键
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
   * 用户名
   */
  @Column(name = "username", nullable = false, unique = true, length = 32)
  private String username;

  /**
   * 密码明文
   */
  @Transient
  private String plaintext;

  /**
   * 密码密文
   */
  @Column(name = "cipher", nullable = false)
  @Setter(AccessLevel.PRIVATE)
  private String cipher;

  /**
   * 用户昵称
   */
  @Column(name = "nickname")
  private String nickname;

  /**
   * 激活时间 该时间之前账号不可用
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

  public User setPlaintext(String plaintext) {
    this.plaintext = plaintext;
    this.cipher = BCrypt.hashpw(plaintext, BCrypt.gensalt());
    return this;
  }

  /**
   * @return 账号可用性
   */
  public boolean isActive() {
    if (this.activateTime == null) {
      return true;
    }
    return Objects.compare(LocalDateTime.now(), this.activateTime, LocalDateTime::compareTo) >= 0;
  }
}
