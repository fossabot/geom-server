package indi.xezzon.geom.auth.domain.dataset.test;

import indi.xezzon.geom.auth.dao.UserGroupMemberDAO;
import indi.xezzon.geom.auth.domain.UserGroupMember;
import indi.xezzon.geom.core.domain.AbstractDataset;
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
public class UserGroupMemberDataset extends AbstractDataset<UserGroupMember> {

  private static final List<UserGroupMember> DATASET = List.of(
      new UserGroupMember()
          .setId("1")
          .setGroupId("1")
          .setUserId("1"),
      new UserGroupMember()
          .setId("2")
          .setGroupId("2")
          .setUserId("2")
  );

  @Autowired
  private UserGroupMemberDataset(UserGroupMemberDAO userGroupMemberDAO) {
    super(DATASET, userGroupMemberDAO);
  }

  /**
   * 从数据集中取一条数据
   * @param predicate 筛选条件
   * @return 数据集中符合条件的任一数据 若没有则返回null
   */
  public static UserGroupMember find(Predicate<UserGroupMember> predicate) {
    return DATASET.stream()
        .filter(predicate)
        .findAny()
        .orElse(null);
  }
}
