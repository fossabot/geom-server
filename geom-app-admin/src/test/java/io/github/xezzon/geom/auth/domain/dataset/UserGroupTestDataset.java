package io.github.xezzon.geom.auth.domain.dataset;

import io.github.xezzon.geom.auth.dao.UserGroupDAO;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.geom.core.domain.AbstractDataset;
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
public final class UserGroupTestDataset extends AbstractDataset<UserGroup> {

  private static final List<UserGroup> DATASET = List.of(
      new UserGroup()
          .setId("1")
          .setCode("test-user")
          .setName("test-group-name")
          .setOwnerId("1"),
      new UserGroup()
          .setId("2")
          .setCode("test-user-2")
          .setName("test-group-name-2")
          .setOwnerId("2")
  );

  @Autowired
  private UserGroupTestDataset(UserGroupDAO userGroupDAO) {
    super(DATASET, userGroupDAO);
  }

  /**
   * 从数据集中取一条数据
   * @param predicate 筛选条件
   * @return 数据集中符合条件的任一数据 若没有则返回null
   */
  public static UserGroup find(Predicate<UserGroup> predicate) {
    return DATASET.stream()
        .filter(predicate)
        .findAny()
        .orElse(null);
  }
}
