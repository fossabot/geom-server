package indi.xezzon.geom.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.domain.QUserGroup;
import indi.xezzon.geom.auth.domain.UserGroup;
import indi.xezzon.geom.auth.service.UserGroupService;
import indi.xezzon.tao.exception.ClientException;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

  private final transient UserGroupDAO userGroupDAO;

  public UserGroupServiceImpl(UserGroupDAO userGroupDAO) {
    this.userGroupDAO = userGroupDAO;
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
  }
}
