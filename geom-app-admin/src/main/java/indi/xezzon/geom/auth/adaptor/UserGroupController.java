package indi.xezzon.geom.auth.adaptor;

import indi.xezzon.geom.auth.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户组接口
 * @author xezzon
 */
@RestController
@RequestMapping("/user-group")
public class UserGroupController {

  private final transient UserGroupService userGroupService;

  @Autowired
  public UserGroupController(UserGroupService userGroupService) {
    this.userGroupService = userGroupService;
  }
}
