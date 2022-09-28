package indi.xezzon.geom.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.auth.domain.dataset.UserTestDataset;
import indi.xezzon.tao.exception.BaseException;
import indi.xezzon.tao.exception.ClientException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

  @Resource
  private transient UserService userService;
  @Resource
  private transient UserDAO userDAO;
  private final transient User currentUser = UserTestDataset.find(Objects::nonNull);

  @BeforeEach
  void setUp() {

  }

  @Test
  @Transactional
  void register() {
    User user = new User()
      .setUsername(RandomUtil.randomString(6))
      .setNickname(RandomUtil.randomString(6))
      .setPlaintext(RandomUtil.randomString(6))
      .setCreateTime(LocalDateTime.now().minusMonths(1));
    User register = userService.register(user);
    /* 测试返回值 */
    Assertions.assertNotNull(register.getId());
    Assertions.assertNotNull(register.getNickname());
    Assertions.assertNull(register.getCipher());
    /* 测试结果 */
    Optional<User> existUser = userDAO.findOne(QUser.user.username.eq(user.getUsername()));
    Assertions.assertTrue(existUser.isPresent());
    Assertions.assertTrue(
        BCrypt.checkpw(user.getPlaintext(), existUser.get().getCipher())
    );
    /* 测试预期异常 */
    Assertions.assertThrows(BaseException.class, () -> userService.register(user));
  }

  @Test
  @Transactional
  void login() {
    /* 正常流程测试 */
    userService.login(currentUser.getUsername(), currentUser.getPlaintext());
    Assertions.assertTrue(StpUtil.isLogin());
    StpUtil.logout();
    /* 预期异常测试 */
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(RandomUtil.randomString(6), currentUser.getPlaintext())
    );
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(currentUser.getUsername(), RandomUtil.randomString(6))
    );
    currentUser.setActivateTime(LocalDateTime.now().plusMonths(1));
    userDAO.save(currentUser);
    Assertions.assertThrows(BaseException.class,
        () -> userService.login(currentUser.getUsername(), currentUser.getPlaintext())
    );
  }

  @Test
  @Transactional
  void updateCipher() {
    /* 正常流程 */
    String newCipher = RandomUtil.randomString(15);
    Assertions.assertDoesNotThrow(() -> userService.updateCipher(currentUser.getId(), newCipher));
    User user1 = userDAO.findById(currentUser.getId()).get();
    Assertions.assertTrue(BCrypt.checkpw(newCipher, user1.getCipher()));
  }

  @Test
  @Transactional
  void forbidUser() {
    /* 正常流程 */
    Assertions.assertDoesNotThrow(
        () -> userService.forbidUser(currentUser.getId(), LocalDateTime.now().plusMonths(1))
    );
    User user1 = userDAO.findById(currentUser.getId()).get();
    Assertions.assertFalse(user1.isActive());
    /* 预期异常 */
    // 禁用用户
    userService.forbidUser(currentUser.getId(), LocalDateTime.now().plusMonths(1));
    Assertions.assertThrows(ClientException.class,
        () -> userService.login(currentUser.getUsername(), currentUser.getPlaintext())
    );
  }

  @Test
  @Transactional
  void checkCipher() {
    /* 正常流程 */
    StpUtil.login(currentUser.getId());
    Assertions.assertTrue(userService.checkCipher(currentUser.getPlaintext()));
    Assertions.assertFalse(userService.checkCipher(RandomUtil.randomString(6)));
    userService.logout(currentUser.getId());
    Assertions.assertFalse(userService.checkCipher(currentUser.getPlaintext()));
  }
}