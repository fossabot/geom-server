package indi.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.dao.UserGroupMemberDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.QUserGroupMember;
import indi.xezzon.geom.auth.domain.User;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.domain.UserGroupMember;
import indi.xezzon.geom.auth.observation.UserRegisterObservation;
import indi.xezzon.geom.auth.service.UserGroupService;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.tao.exception.ClientException;
import indi.xezzon.tao.observer.ObserverContext;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

  private final transient UserGroupDAO userGroupDAO;
  private final transient UserGroupMemberDAO userGroupMemberDAO;
  private final transient UserService userService;

  public UserGroupServiceImpl(
      UserGroupDAO userGroupDAO,
      UserGroupMemberDAO userGroupMemberDAO,
      UserService userService
  ) {
    this.userGroupDAO = userGroupDAO;
    this.userGroupMemberDAO = userGroupMemberDAO;
    this.userService = userService;
  }

  @PostConstruct
  public void init() {
    ObserverContext.register(UserRegisterObservation.class, this::handleObservation);
  }

  private void handleObservation(UserRegisterObservation observation) {
    UserGroup userGroup = new UserGroup()
        .setCode(observation.username())
        .setName(observation.nickname());
    StpUtil.switchTo(observation.userId(),
        () -> this.insert(userGroup)
    );
  }

  @Override
  public void insert(UserGroup userGroup) {
    /* 前置校验 */
    // 登录状态
    StpUtil.checkLogin();
    // code 不能重复
    boolean exists = userGroupDAO.exists(QUserGroup.userGroup.code.eq(userGroup.getCode()));
    if (exists) {
      throw new ClientException("用户组" + userGroup.getCode() + "已存在");
    }

    userGroup.setId(null)
        .setOwnerId(StpUtil.getLoginId(null));
    userGroupDAO.save(userGroup);
    /* 后置操作 */
    // 将owner添加到成员列表
    this.addMember(userGroup.getId(), userGroup.getOwnerId());
  }

  @Override
  public void transfer(String groupId, String userId) {
    /* 前置校验 */
    UserGroup userGroup = this.getById(groupId);
    User user = userService.getById(userId);
    if (userGroup == null) {
      throw new ClientException("用户组不存在");
    }
    if (!Objects.equals(userGroup.getOwnerId(), StpUtil.getLoginId())) {
      throw new ClientException("无权转让该用户组");
    }
    if (user == null) {
      throw new ClientException("用户不存在");
    }
    if (!user.isActive()) {
      throw new ClientException("无法转让给该用户");
    }

    userGroupDAO.update(new UserGroup()
        .setId(groupId)
        .setOwnerId(userId)
    );
  }

  @Override
  public UserGroup getById(String id) {
    return userGroupDAO.findById(id).orElse(null);
  }

  @Override
  public void addMember(String groupId, String userId) {
    final QUserGroupMember qUserGroupMember = QUserGroupMember.userGroupMember;
    boolean exists = userGroupMemberDAO.exists(
        qUserGroupMember.groupId.eq(groupId)
            .and(qUserGroupMember.userId.eq(userId))
    );
    if (exists) {
      // 用户已在用户组中直接返回
      return;
    }
    userGroupMemberDAO.save(new UserGroupMember()
        .setGroupId(groupId)
        .setUserId(userId)
    );
  }

  @Override
  public void removeMember(String groupId, String userId) {
    /* 前置校验 */
    Optional<UserGroup> userGroup = userGroupDAO.findById(groupId);
    if (userGroup.isEmpty()) {
      throw new ClientException("用户组不存在");
    }
    if (Objects.equals(userGroup.get().getOwnerId(), userId)) {
      throw new ClientException("无法移除所有者");
    }

    userGroupMemberDAO.delete(
        QUserGroupMember.userGroupMember.groupId.eq(groupId)
            .and(QUserGroupMember.userGroupMember.userId.eq(userId))
    );
  }
}
