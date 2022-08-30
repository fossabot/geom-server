package indi.xezzon.geom.dao.system;

import indi.xezzon.geom.domain.system.UserDO;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import javax.transaction.Transactional;
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
    userDAO.save(new UserDO()
        .setUsername("xezzon")
        .setCipher(BCrypt.hashpw("hello", BCrypt.gensalt()))
        .setNickname("world")
        .setActivateTime(current)
        .setCreateTime(current)
        .setUpdateTime(current)
    );
  }
}