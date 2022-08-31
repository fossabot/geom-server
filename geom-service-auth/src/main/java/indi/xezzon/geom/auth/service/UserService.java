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
}
