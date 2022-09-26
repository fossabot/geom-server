package indi.xezzon.geom.auth.service;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.UserGroup;
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
}