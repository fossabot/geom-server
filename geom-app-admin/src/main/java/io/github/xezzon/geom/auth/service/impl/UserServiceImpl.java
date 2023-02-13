package io.github.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import io.github.xezzon.geom.auth.dao.wrapper.WrappedUserDAO;
import io.github.xezzon.geom.auth.domain.QUser;
import io.github.xezzon.geom.auth.domain.User;
import io.github.xezzon.geom.auth.observation.UserRegisterObservation;
import io.github.xezzon.geom.auth.service.UserService;
import io.github.xezzon.tao.exception.ClientException;
import io.github.xezzon.tao.observer.ObserverContext;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class UserServiceImpl implements UserService {

  private final transient WrappedUserDAO userDAO;

  @Autowired
  public UserServiceImpl(WrappedUserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public User register(@NotNull User user) {
    String username = user.getUsername();
    boolean exists = userDAO.get().exists(QUser.user.username.eq(username));
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

    userDAO.get().save(user);
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
    if (activateTime.isAfter(LocalDateTime.now())) {
      StpUtil.kickout(userId);
    }
  }

  @Override
  public User getById(String id) {
    if (id == null) {
      return null;
    }
    return userDAO.get().findById(id).orElse(null);
  }

  @Override
  public User getByUsername(String username) {
    if (username == null) {
      return null;
    }
    return userDAO.get().findOne(QUser.user.username.eq(username)).orElse(null);
  }
}
