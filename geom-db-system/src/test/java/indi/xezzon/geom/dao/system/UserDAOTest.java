package indi.xezzon.geom.dao.system;

import indi.xezzon.geom.domain.system.QUserDO;
import indi.xezzon.geom.domain.system.UserDO;
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
class UserDAOTest {

  @Resource
  private transient UserDAO userDAO;

  @Test
  @Transactional
  void save() {
    UserDO userDO = new UserDO()
        .setUsername("xezzon")
        .setCipher("hello")
        .setNickname("world")
        .setActivateTime(LocalDateTime.now())
        .setCreateTime(LocalDateTime.now().minusMonths(1));
    userDAO.save(userDO);

    Optional<UserDO> xezzon = userDAO.findOne(QUserDO.userDO.username.eq("xezzon"));

    Assertions.assertTrue(xezzon.isPresent());
    // 创建时间由 JPA 自动生成 手动设置无效
    Assertions.assertTrue(
        xezzon.map(UserDO::getCreateTime)
            .filter(createTime -> LocalDateTime.now().minusHours(1).compareTo(createTime) < 0)
            .isPresent()
    );
  }
}