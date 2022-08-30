package indi.xezzon.geom.dao.system;

import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.convert.UserConvert;
import indi.xezzon.geom.domain.system.UserDO;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserDAOTest {

  @Resource
  private UserDAO userDAO;

  @Test
  @Transactional
  public void test() {
    LocalDateTime current = LocalDateTime.now();
    UserDO userDO = new UserDO()
        .setUsername("xezzon")
        .setCipher(BCrypt.hashpw("hello", BCrypt.gensalt()))
        .setNickname("world")
        .setActivateTime(current);
    userDAO.save(userDO);

    User user = UserConvert.INSTANCE.into(userDO);
    Assertions.assertNotNull(user.getId());
  }
}