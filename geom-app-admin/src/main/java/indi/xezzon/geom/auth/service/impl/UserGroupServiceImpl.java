package indi.xezzon.geom.auth.service.impl;

import indi.xezzon.geom.auth.dao.UserGroupDAO;
import indi.xezzon.geom.auth.service.UserGroupService;
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
}
