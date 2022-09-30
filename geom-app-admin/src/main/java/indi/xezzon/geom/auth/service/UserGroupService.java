package indi.xezzon.geom.auth.service;

import indi.xezzon.geom.auth.domain.UserGroup;
import java.util.List;

/**
 * @author xezzon
 */
public interface UserGroupService {

  /**
   * 新增用户组信息
   * @param userGroup 用户组信息
   */
  void insert(UserGroup userGroup);

  /**
   * 转让用户组所有权
   * @param groupId 被转让的用户组
   * @param userId 受转让的用户
   */
  void transfer(String groupId, String userId);

  /**
   * 根据主键查询用户组信息
   * @param id 用户组主键
   * @return 用户组信息 未查到记录则返回null
   */
  UserGroup getById(String id);

  /**
   * 用户组添加成员
   * @param groupId 用户组主键
   * @param userId 用户主键
   */
  void addMember(String groupId, String userId);

  /**
   * 用户组移除成员
   * @param groupId 用户组主键
   * @param userId 用户主键
   */
  void removeMember(String groupId, String userId);

  /**
   * 根据用户组编码搜索
   * @param code 用户组编码
   * @return 用户组信息 未找到则返回null
   */
  UserGroup getByCode(String code);

  /**
   * 获取用户所在用户组清单
   * @param userId 用户主键
   * @return 用户所在用户组
   */
  List<UserGroup> listByUserId(String userId);

  /**
   * 切换当前用户组（对当前会话有效）
   * @param groupCode 用户组编码
   */
  void switchGroup(String groupCode);
}
