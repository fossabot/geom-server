package indi.xezzon.geom.auth.adaptor;

import indi.xezzon.geom.auth.domain.UpdateCipherQuery;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.tao.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基础信息相关操作
 * @author xezzon
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private final transient UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PatchMapping("/cipher")
  public void updateCipher(UpdateCipherQuery query) {
    boolean checked = userService.checkCipher(query.getOldCipher());
    if (!checked) {
      throw new ClientException("密码错误");
    }
    // TODO: 用户信息局部更新
  }
}
