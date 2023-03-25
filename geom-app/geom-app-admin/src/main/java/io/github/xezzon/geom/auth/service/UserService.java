package io.github.xezzon.geom.auth.service;

import io.github.xezzon.geom.auth.domain.User;
import org.jetbrains.annotations.NotNull;

/**
 * @author xezzon
 */
public interface UserService {

  /**
   * 用户注册
   * @param user 用户名 密码 昵称
   * @return ID 昵称
   */
  User register(@NotNull User user);

  /**
   * 根据用户名获取用户信息
   * @param username 用户名
   * @return 用户信息
   */
  User getByUsername(String username);
}
