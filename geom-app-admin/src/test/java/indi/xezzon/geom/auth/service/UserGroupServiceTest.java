package indi.xezzon.geom.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.dao.UserGroupMemberDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.QUserGroupMember;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.tao.exception.ClientException;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UserGroupServiceTest {

  @Resource
  private transient UserGroupService userGroupService;
  @Resource
  private transient UserGroupDAO userGroupDAO;
  @Resource
  private transient UserService userService;
  @Resource
  private transient UserGroupMemberDAO userGroupMemberDAO;

  @BeforeEach
  void setUp() {
    userService.login("test-user", "test@123");
  }

  @Test
  void insert() {
    String code = RandomUtil.randomString(6);
    String name = RandomUtil.randomString(6);
    UserGroup userGroup = new UserGroup()
        .setCode(code)
        .setName(name);
    userGroupService.insert(userGroup);
    UserGroup userGroup1 = userGroupDAO.getById(userGroup.getId());
    Assertions.assertNotNull(userGroup1);
  }

  @Test
  void handleUserRegisterObservation() {
    String username = RandomUtil.randomString(6);
    String cipher = RandomUtil.randomString(6);
    User register = userService.register(new User()
        .setUsername(username)
        .setCipher(cipher)
    );
    Optional<UserGroup> userGroup = userGroupDAO.findOne(QUserGroup.userGroup.code.eq(username));
    Assertions.assertTrue(userGroup.isPresent());
    Assertions.assertEquals(register.getId(), userGroup.get().getOwnerId());
  }

  @Test
  void transfer() {
    final String userId = "1";
    final String userId1 = "2";
    /* 数据准备 */
    UserGroup userGroup = new UserGroup()
        .setCode(RandomUtil.randomString(6))
        .setName(RandomUtil.randomString(6));
    userGroupService.insert(userGroup);
    /* 正常流程 */
    Assertions.assertDoesNotThrow(() -> userGroupService.transfer(userGroup.getId(), userId1));
    UserGroup userGroup1 = userGroupDAO.findById(userGroup.getId()).get();
    Assertions.assertEquals(userId1, userGroup1.getOwnerId());
    userGroupMemberDAO.findOne(
        QUserGroupMember.userGroupMember.groupId.eq(userGroup.getId())
            .and(QUserGroupMember.userGroupMember.userId.eq(userId))
    );
    /* 预期异常 */
    // 无权转让
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(userGroup.getId(), userId)
    );
    StpUtil.switchTo(userId1);
    // 用户组不存在
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(RandomUtil.randomString(6), userId)
    );
    // 受转让者不存在
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(userGroup.getId(), RandomUtil.randomString(6))
    );
    StpUtil.endSwitch();
  }

  @Test
  void addMember() {
    /* 数据准备 */
    User user = new User()
        .setUsername(RandomUtil.randomString(6))
        .setCipher(RandomUtil.randomString(15));
    userService.register(user);
    UserGroup userGroup = new UserGroup()
        .setCode(RandomUtil.randomString(6))
        .setName(RandomUtil.randomString(6));
    userGroupService.insert(userGroup);
    /* 正常流程 */
    Assertions.assertDoesNotThrow(
        () -> userGroupService.addMember(userGroup.getId(), user.getId())
    );
    // 重复添加
    Assertions.assertDoesNotThrow(
        () -> userGroupService.addMember(userGroup.getId(), user.getId())
    );
  }
}