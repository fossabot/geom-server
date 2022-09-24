package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.auth.domain.User;
import java.time.LocalDateTime;
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

  /**
   * 校验当前用户的密码
   * @param cipher 用户输入的密码
   * @return 用户输入的密码是否正确
   */
  boolean checkCipher(String cipher);

  /**
   * 修改密码
   * @param userId 用户ID
   * @param cipher 新密码
   */
  void updateCipher(String userId, String cipher);

  /**
   * 禁用用户
   * @param userId 用户ID
   * @param activateTime 禁用至
   */
  void forbidUser(String userId, LocalDateTime activateTime);
}
