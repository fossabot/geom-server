package indi.xezzon.geom.auth.dao;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.domain.QUser;
import indi.xezzon.geom.auth.domain.User;
import java.time.Duration;
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
    User user = new User()
        .setUsername(RandomUtil.randomString(6))
        .setPlaintext(RandomUtil.randomString(6))
        .setNickname(RandomUtil.randomString(6))
        .setActivateTime(LocalDateTime.now())
        .setCreateTime(LocalDateTime.now().minusMonths(1));
    userDAO.save(user);

    Optional<User> existUser = userDAO.findOne(QUser.user.username.eq(user.getUsername()));

    Assertions.assertTrue(existUser.isPresent());
    // 创建时间由 JPA 自动生成 手动设置无效
    Assertions.assertTrue(
        existUser.map(User::getCreateTime)
            .filter(createTime -> LocalDateTime.now().minusHours(1).compareTo(createTime) < 0)
            .isPresent()
    );
  }

  @Test
  void update() throws InterruptedException {
    // 准备数据
    User user = new User()
        .setUsername(RandomUtil.randomString(6))
        .setPlaintext(RandomUtil.randomString(6))
        .setNickname(RandomUtil.randomString(6))
        .setActivateTime(LocalDateTime.now());
    userDAO.save(user);
    // 正常流程
    Thread.sleep(1000L);
    String newCipher = RandomUtil.randomString(8);
    boolean updated = userDAO.update(new User()
        .setId(user.getId())
        .setPlaintext(newCipher)
    );
    Assertions.assertTrue(updated);
    User user1 = userDAO.findOne(QUser.user.username.eq(user.getUsername())).get();
    Assertions.assertEquals(user.getNickname(), user1.getNickname());
    Assertions.assertTrue(Duration.between(
        user.getUpdateTime(),
        user1.getUpdateTime()
    ).getSeconds() >= 1);
  }
}