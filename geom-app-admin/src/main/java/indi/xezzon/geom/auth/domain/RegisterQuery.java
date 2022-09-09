package indi.xezzon.geom.auth.domain;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Validated
public class RegisterQuery {

  /**
   * 用户名
   */
  @Pattern(
      regexp = "^[a-z0-9_-]{3,15}$",
      message = "用户名必须是3~15位 小写字母/数字/下划线 组成的字符串"
  )
  private String username;
  /**
   * 密码
   */
  @Pattern(
      regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![@!#$%^&*]+$)[0-9a-zA-Z@!#$%^&*]{8,}$",
      message = "密码由至少8位有效字符构成，且包含 大小写字符/数字/特殊字符(@!#$%^&*) 中至少两类"
  )
  private String cipher;
  /**
   * 用户昵称
   */
  private String nickname;
}
