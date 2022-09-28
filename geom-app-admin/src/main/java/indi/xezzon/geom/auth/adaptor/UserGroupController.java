package indi.xezzon.geom.auth.adaptor;

import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  /**
   * @param userGroup 用户组信息 包括编码与名称
   */
  @PostMapping("")
  public void add(@RequestBody UserGroup userGroup) {
    userGroupService.insert(userGroup);
  }

  /**
   * 转让用户组
   */
  @PatchMapping("/{groupId}/owner/{userId}")
  public void transfer(@PathVariable String groupId, @PathVariable String userId) {
    userGroupService.transfer(groupId, userId);
  }

  /**
   * 用户组添加成员
   * @param groupId 用户组主键
   * @param userId 用户主键
   */
  @PostMapping("/{groupId}/member/{userId}")
  public void enroll(@PathVariable String groupId, @PathVariable String userId) {
    userGroupService.addMember(groupId, userId);
  }
}
