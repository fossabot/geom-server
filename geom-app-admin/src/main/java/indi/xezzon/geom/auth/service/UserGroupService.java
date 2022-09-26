package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.auth.domain.UserGroup;

/**
 * @author xezzon
 */
public interface UserGroupService {

  /**
   * 新增用户组信息
   * @param userGroup 用户组信息
   */
  void insert(UserGroup userGroup);
}
