package indi.xezzon.geom.auth.domain.dataset;

import indi.xezzon.geom.auth.dao.UserDAO;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.core.domain.AbstractDataset;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
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
