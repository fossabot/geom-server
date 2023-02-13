package io.github.xezzon.geom.auth.adaptor;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import io.github.xezzon.geom.auth.domain.UserGroup;
import io.github.xezzon.geom.auth.service.AuthService;
import io.github.xezzon.geom.auth.service.UserGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private final transient AuthService authService;

  @Autowired
  public UserGroupController(UserGroupService userGroupService, AuthService authService) {
    this.userGroupService = userGroupService;
    this.authService = authService;
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

  /**
   * 用户组移除成员
   * @param groupId 用户组主键
   * @param userId 用户主键
   */
  @DeleteMapping("/{groupId}/member/{userId}")
  public void dismiss(@PathVariable String groupId, @PathVariable String userId) {
    userGroupService.removeMember(groupId, userId);
  }

  /**
   * 获取当前用户所在用户组
   * @return 用户组列表
   */
  @GetMapping("")
  @SaCheckLogin
  public List<UserGroup> listUserGroup() {
    return userGroupService.listByUserId(StpUtil.getLoginId(null));
  }

  /**
   * 切换当前会话的用户组
   * @param groupCode 用户组编码
   */
  @PutMapping("/{groupCode}/current")
  public void switchGroup(@PathVariable String groupCode) {
    authService.switchGroup(groupCode);
  }
}
