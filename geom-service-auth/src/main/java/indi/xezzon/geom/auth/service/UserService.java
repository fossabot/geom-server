package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.domain.User;
import org.jetbrains.annotations.NotNull;

public interface UserService {

  /**
   * 用户注册
   * @param user 用户名 密码 昵称
   * @return ID 昵称
   */
  User register(@NotNull User user);

  /**
   * 用户登录
   * @param username 用户名
   * @param cipher 密码
   */
  void login(String username, String cipher);
}
