package indi.xezzon.geom.auth.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.dao.system.UserDAO;
import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.geom.domain.system.UserDO;
import indi.xezzon.tao.exception.BaseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
}