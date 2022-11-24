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

  /**
   * 根据用户主键获取用户信息
   * @param id 用户主键
   * @return 用户信息 找不到记录时返回null
   */
  User getById(String id);

  /**
   * 根据用户名获取用户信息
   * @param username 用户名
   * @return 用户信息 找不到记录时返回null
   */
  User getByUsername(String username);
}
