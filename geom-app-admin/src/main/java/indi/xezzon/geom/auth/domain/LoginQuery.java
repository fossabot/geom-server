package indi.xezzon.geom.auth.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginQuery {

  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String cipher;
}
