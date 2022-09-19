package indi.xezzon.geom.auth.dao;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.domain.QUserDO;
import indi.xezzon.geom.auth.domain.UserDO;
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
    String username = RandomUtil.randomString(6);
    String cipher = RandomUtil.randomString(6);
    String nickname = RandomUtil.randomString(6);
    UserDO userDO = new UserDO()
        .setUsername(username)
        .setCipher(cipher)
        .setNickname(nickname)
        .setActivateTime(LocalDateTime.now())
        .setCreateTime(LocalDateTime.now().minusMonths(1));
    userDAO.save(userDO);

    Optional<UserDO> xezzon = userDAO.findOne(QUserDO.userDO.username.eq(username));

    Assertions.assertTrue(xezzon.isPresent());
    // 创建时间由 JPA 自动生成 手动设置无效
    Assertions.assertTrue(
        xezzon.map(UserDO::getCreateTime)
            .filter(createTime -> LocalDateTime.now().minusHours(1).compareTo(createTime) < 0)
            .isPresent()
    );
  }
}