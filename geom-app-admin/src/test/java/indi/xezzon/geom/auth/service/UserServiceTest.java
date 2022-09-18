package indi.xezzon.geom.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUserDO;
import indi.xezzon.geom.auth.domain.UserDO;
import indi.xezzon.geom.domain.User;
import indi.xezzon.tao.exception.BaseException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

  @Resource
  private transient UserService userService;
  @Resource
  private transient UserDAO userDAO;

  @Test
  @Transactional
  void register() {
    final String username = RandomUtil.randomString(6);
    final String cipher = RandomUtil.randomString(6);
    User user = new User();
    user.setUsername(username);
    user.setNickname(RandomUtil.randomString(6));
    user.setCipher(cipher);
    user.setCreateTime(LocalDateTime.now().minusMonths(1));
    User register = userService.register(user);
    /* 测试返回值 */
    Assertions.assertNotNull(register.getId());
    Assertions.assertNotNull(register.getNickname());
    Assertions.assertNull(register.getCipher());
    /* 测试结果 */
    Optional<UserDO> existUser = userDAO.findOne(QUserDO.userDO.username.eq(username));
    Assertions.assertTrue(existUser.isPresent());
    Assertions.assertTrue(BCrypt.checkpw(cipher, existUser.map(UserDO::getCipher).get()));
    /* 测试预期异常 */
    Assertions.assertThrows(BaseException.class, () -> userService.register(user));
  }

  @Test
  @Transactional
  void login() {
    /* 准备数据 */
    String username = RandomUtil.randomString(6);
    String cipher = RandomUtil.randomString(6);
    User user = new User()
        .setUsername(username)
        .setCipher(cipher);
    userService.register(user);
    /* 正常流程测试 */
    userService.login(username, cipher);
    Assertions.assertTrue(StpUtil.isLogin());
    StpUtil.logout();
    /* 预期异常测试 */
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(RandomUtil.randomString(6), cipher)
    );
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(username, RandomUtil.randomString(6))
    );
    user.setActivateTime(LocalDateTime.now().plusMonths(1));
    userDAO.save(user);
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(username, cipher)
    );
  }
}