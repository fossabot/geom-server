package indi.xezzon.geom.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户
 * @author xezzon
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class User {

  /**
   * 用户主键
   */
  private String id;

  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String cipher;

  /**
   * 用户昵称
   */
  private String nickname;

  /**
   * 激活时间
   * 该时间之前账号不可用
   */
  private LocalDateTime activateTime;

  /**
   * 记录创建时间
   */
  private LocalDateTime createTime;

  /**
   * 最后更新时间
   */
  private LocalDateTime updateTime;

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
