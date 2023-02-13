package io.github.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import io.github.xezzon.geom.auth.constant.SessionConstant;
import io.github.xezzon.geom.auth.domain.User;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.geom.auth.service.AuthService;
import io.github.xezzon.geom.auth.service.UserGroupService;
import io.github.xezzon.geom.auth.service.UserService;
import io.github.xezzon.tao.exception.ClientException;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class AuthServiceImpl implements AuthService {

  private transient final UserService userService;
  private transient final UserGroupService userGroupService;

  public AuthServiceImpl(UserService userService, UserGroupService userGroupService) {
    this.userService = userService;
    this.userGroupService = userGroupService;
  }

  @Override
  public void login(String username, String cipher) {
    if (StpUtil.isLogin()) {
      return;
    }
    User user = userService.getByUsername(username);
    if (user == null) {
      throw new ClientException("用户名或密码错误");
    }
    if (!user.isActive()) {
      throw new ClientException("用户被禁用");
    }
    if (!BCrypt.checkpw(cipher, user.getCipher())) {
      throw new ClientException("用户名或密码错误");
    }
    /* 执行主流程 */
    StpUtil.login(user.getId());
    /* 后置操作 */
    // 保存当前用户组
    this.switchGroup(user.getUsername());
  }

  @Override
  public User getCurrentUser() {
    StpUtil.checkLogin();
    return userService.getById(StpUtil.getLoginId(null));
  }

  @Override
  public void logout(String userId) {
    StpUtil.logout(userId);
  }

  @Override
  public boolean checkCipher(String cipher) {
    String id = StpUtil.getLoginId(null);
    if (id == null) {
      return false;
    }
    User user = userService.getById(id);
    if (user == null) {
      return false;
    }
    return BCrypt.checkpw(cipher, user.getCipher());
  }

  @Override
  public void switchGroup(String groupCode) {
    /* 前置校验 */
    StpUtil.checkLogin();
    /* 执行主流程 */
    UserGroup userGroup = userGroupService.getByCode(groupCode);
    StpUtil.getTokenSession()
        .set(SessionConstant.CURRENT_GROUP, userGroup);
  }

  @Override
  public UserGroup getCurrentGroup() {
    StpUtil.checkLogin();
    return StpUtil.getTokenSession()
        .getModel(SessionConstant.CURRENT_GROUP, UserGroup.class);
  }
}
