package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.domain.User;
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
   * 用户登录
   * @param username 用户名
   * @param cipher 密码
   */
  void login(String username, String cipher);

  /**
   * 用户注销
   * @param userId 用户主键
   */
  void logout(String userId);
}
