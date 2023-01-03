package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.auth.domain.UserGroup;

/**
 * 认证授权
 * @author xezzon
 */
public interface AuthService {

  /**
   * 用户登录
   * @param username 用户名
   * @param cipher 密码
   */
  void login(String username, String cipher);

  /**
   * 查询当前登录用户
   * @return 当前用户账号信息
   */
  User getCurrentUser();

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
   * 切换当前用户组（对当前会话有效）
   * @param groupCode 用户组编码
   */
  void switchGroup(String groupCode);

  /**
   * 获取当前用户组
   * @return 当前用户组
   */
  UserGroup getCurrentGroup();
}
