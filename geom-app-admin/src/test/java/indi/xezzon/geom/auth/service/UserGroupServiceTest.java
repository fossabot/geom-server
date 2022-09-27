package indi.xezzon.geom.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
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
    /* 数据准备 */
    UserGroup userGroup = new UserGroup()
        .setCode(RandomUtil.randomString(6))
        .setName(RandomUtil.randomString(6));
    userGroupService.insert(userGroup);
    /* 正常流程 */
    Assertions.assertDoesNotThrow(() -> userGroupService.transfer(userGroup.getId(), "2"));
    UserGroup userGroup1 = userGroupDAO.findById(userGroup.getId()).get();
    Assertions.assertEquals("2", userGroup1.getOwnerId());
    /* 预期异常 */
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(userGroup.getId(), "1")
    );
    StpUtil.switchTo("2");
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(RandomUtil.randomString(6), "1")
    );
    Assertions.assertThrows(ClientException.class,
        () -> userGroupService.transfer(userGroup.getId(), RandomUtil.randomString(6))
    );
    StpUtil.endSwitch();
  }
}