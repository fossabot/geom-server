package indi.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.constant.SessionConstant;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.observation.UserRegisterObservation;
import indi.xezzon.geom.auth.service.UserGroupService;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.tao.exception.ClientException;
import indi.xezzon.tao.observer.ObserverContext;
import java.time.LocalDateTime;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class UserServiceImpl implements UserService {

  private final transient UserDAO userDAO;
  private final transient UserGroupService userGroupService;

  @Autowired
  public UserServiceImpl(
      UserDAO userDAO,
      @Lazy UserGroupService userGroupService
  ) {
    this.userDAO = userDAO;
    this.userGroupService = userGroupService;
  }

  @Override
  public User register(@NotNull User user) {
    String username = user.getUsername();
    boolean exists = userDAO.exists(QUser.user.username.eq(username));
    if (exists) {
      throw new ClientException("用户名" + username + "已注册");
    }
    
    if (user.getNickname() == null) {
      user.setNickname(username);
    }
    if (user.getActivateTime() == null) {
      user.setActivateTime(LocalDateTime.now());
    }
    user.setId(null);

    userDAO.save(user);
    /* 后置操作 */
    ObserverContext.post(new UserRegisterObservation(
        user.getId(),
        user.getUsername(),
        user.getNickname()
    ));

    return new User()
        .setId(user.getId())
        .setNickname(user.getNickname());
  }

  @Override
  public void login(String username, String cipher) {
    if (StpUtil.isLogin()) {
      return;
    }
    User user = userDAO.findOne(QUser.user.username.eq(username))
        .orElseThrow(() -> new ClientException("用户名或密码错误"));
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
    UserGroup userGroup = userGroupService.getByCode(user.getUsername());
    StpUtil.getTokenSession().set(SessionConstant.CURRENT_GROUP, userGroup);
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
    Optional<User> user = userDAO.findById(id);
    if (user.isEmpty()) {
      return false;
    }
    return BCrypt.checkpw(cipher, user.get().getCipher());
  }

  @Override
  public void updateCipher(String userId, String cipher) {
    userDAO.update(new User()
        .setPlaintext(cipher)
        .setId(userId)
    );
  }

  @Override
  public void forbidUser(String userId, @NotNull LocalDateTime activateTime) {
    userDAO.update(new User()
        .setActivateTime(activateTime)
        .setId(userId)
    );
    /* 后置处理 */
    // 若禁用时间大于当前时间 将该账号踢下线
    if (activateTime.compareTo(LocalDateTime.now()) > 0) {
      StpUtil.logout(userId);
    }
  }

  @Override
  public User getById(String id) {
    return userDAO.findById(id).orElse(null);
  }
}
