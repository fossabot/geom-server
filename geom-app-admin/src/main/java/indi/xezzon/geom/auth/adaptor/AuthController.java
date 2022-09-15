package indi.xezzon.geom.auth.adaptor;

import indi.xezzon.geom.auth.domain.LoginQuery;
import indi.xezzon.geom.auth.domain.RegisterQuery;
import indi.xezzon.geom.auth.domain.convert.UserConvert;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.geom.domain.User;
import indi.xezzon.tao.logger.LogRecord;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口
 */
@RestController
@RequestMapping
public class AuthController {

  private final transient UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  /**
   * 用户注册
   * @param user 用户名 密码 等
   * @return 用户ID 用户昵称
   */
  @PostMapping("/register")
  public User register(@RequestBody @Valid RegisterQuery user) {
    return userService.register(UserConvert.INSTANCE.from(user));
  }

  /**
   * 用户登录
   * @param query 用户名 密码
   */
  @PostMapping("/login")
  @LogRecord
  public void login(@RequestBody LoginQuery query) {
    userService.login(query.getUsername(), query.getCipher());
  }
}
