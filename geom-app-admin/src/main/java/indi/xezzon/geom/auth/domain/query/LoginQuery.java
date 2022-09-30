package indi.xezzon.geom.auth.domain.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xezzon
 */
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
