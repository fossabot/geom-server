package indi.xezzon.geom.auth.service;

import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.dao.system.UserDAO;
import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.geom.domain.system.UserDO;
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
  private UserService userService;
  @Resource
  private UserDAO userDAO;

  @Test
  @Transactional
  void register() {
    final String username = "xezzon";
    final String cipher = "123456";
    User user = new User();
    user.setUsername(username);
    user.setNickname("hello");
    user.setCipher(cipher);
    user.setCreateTime(LocalDateTime.now().minusMonths(1));
    User register = userService.register(user);
    /* 测试返回值 */
    Assertions.assertNotNull(register.getId());
    Assertions.assertNotNull(register.getNickname());
    Assertions.assertNull(register.getCipher());
    /* 测试结果 */
    Optional<UserDO> xezzon = userDAO.findOne(QUserDO.userDO.username.eq(username));
    Assertions.assertTrue(xezzon.isPresent());
    Assertions.assertTrue(BCrypt.checkpw(cipher, xezzon.map(UserDO::getCipher).get()));
    /* 测试预期异常 */
    Assertions.assertThrows(BaseException.class, () -> userService.register(user));
  }
}