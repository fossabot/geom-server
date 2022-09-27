package indi.xezzon.geom.auth.domain.dataset;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.core.domain.AbstractDataset;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author xezzon
 */
@Component
public final class UserDataset extends AbstractDataset<User> {

  private static final List<User> DATASET = Collections.emptyList();

  @Autowired
  private UserDataset(UserDAO userDAO) {
    super(DATASET, userDAO);
  }
}

@Component
@Profile({"dev", "test"})
final class UserTestDataset extends AbstractDataset<User> {

  private static final List<User> DATASET = List.of(
      new User()
          .setId("1")
          .setUsername("test-user")
          .setCipher(BCrypt.hashpw("test@123"))
          .setNickname(RandomUtil.randomString(6))
          .setActivateTime(LocalDateTime.now()),
      new User()
          .setId("2")
          .setUsername("test-user-2")
          .setCipher(BCrypt.hashpw("test@456"))
          .setNickname(RandomUtil.randomString(6))
          .setActivateTime(LocalDateTime.now())
  );

  @Autowired
  private UserTestDataset(UserDAO userDAO) {
    super(DATASET, userDAO);
  }
}
