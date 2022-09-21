package indi.xezzon.geom.auth.dao;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
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
    User user = new User()
        .setUsername(username)
        .setCipher(cipher)
        .setNickname(nickname)
        .setActivateTime(LocalDateTime.now())
        .setCreateTime(LocalDateTime.now().minusMonths(1));
    userDAO.save(user);

    Optional<User> existUser = userDAO.findOne(QUser.user.username.eq(username));

    Assertions.assertTrue(existUser.isPresent());
    // 创建时间由 JPA 自动生成 手动设置无效
    Assertions.assertTrue(
        existUser.map(User::getCreateTime)
            .filter(createTime -> LocalDateTime.now().minusHours(1).compareTo(createTime) < 0)
            .isPresent()
    );
  }
}