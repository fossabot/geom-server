package indi.xezzon.geom.auth.domain.dataset;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.core.domain.AbstractDataset;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author xezzon
 */
@Component
@Profile({"dev", "test"})
public final class UserTestDataset extends AbstractDataset<User> {

  private static final List<User> DATASET = List.of(
      new User()
          .setId("1")
          .setUsername("test-user")
          .setPlaintext("test@123")
          .setNickname(RandomUtil.randomString(6))
          .setActivateTime(LocalDateTime.now()),
      new User()
          .setId("2")
          .setUsername("test-user-2")
          .setPlaintext("test@456")
          .setNickname(RandomUtil.randomString(6))
          .setActivateTime(LocalDateTime.now())
  );

  @Autowired
  private UserTestDataset(UserDAO userDAO) {
    super(DATASET, userDAO);
  }

  /**
   * 从数据集中取一条数据
   * @param predicate 筛选条件
   * @return 数据集中符合条件的任一数据 若没有则返回null
   */
  public static User find(Predicate<User> predicate) {
    return DATASET.stream()
        .filter(predicate)
        .findAny()
        .orElse(null);
  }
}
