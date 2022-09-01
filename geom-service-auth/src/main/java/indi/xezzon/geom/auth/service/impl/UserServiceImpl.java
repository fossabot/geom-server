package indi.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.geom.dao.system.UserDAO;
import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.convert.UserConvert;
import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.geom.domain.system.UserDO;
import indi.xezzon.tao.exception.BaseException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final transient UserDAO userDAO;

  @Autowired
  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public User register(@NotNull User user) {
    String username = user.getUsername();
    boolean exists = userDAO.exists(QUserDO.userDO.username.eq(username));
    if (exists) {
      throw new BaseException("用户名" + username + "已注册");
    }
    
    if (user.getNickname() == null) {
      user.setNickname(username);
    }
    if (user.getActivateTime() == null) {
      user.setActivateTime(LocalDateTime.now());
    }
    user.setId(null);
    user.setCipher(BCrypt.hashpw(user.getCipher(), BCrypt.gensalt()));

    userDAO.save(user);

    return new User()
        .setId(user.getId())
        .setNickname(user.getNickname());
  }

  @Override
  public void login(String username, String cipher) {
    Optional<UserDO> userDO = userDAO.findOne(QUserDO.userDO.username.eq(username));
    User user = userDO.map(UserConvert.INSTANCE::into)
        .orElseThrow(() -> new BaseException("用户名或密码错误"));
    if (!user.isActive()) {
      throw new BaseException("用户被禁用");
    }
    if (!BCrypt.checkpw(cipher, user.getCipher())) {
      throw new BaseException("用户名或密码错误");
    }

    StpUtil.login(user.getId());
  }
}
