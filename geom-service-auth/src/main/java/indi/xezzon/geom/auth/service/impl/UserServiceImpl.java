package indi.xezzon.geom.auth.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.geom.dao.system.UserDAO;
import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.tao.exception.BaseException;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDAO userDAO;

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
}
