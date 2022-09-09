package indi.xezzon.geom.auth.adaptor;

import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.geom.domain.User;
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
  public User register(@RequestBody User user) {
    return userService.register(user);
  }
}
