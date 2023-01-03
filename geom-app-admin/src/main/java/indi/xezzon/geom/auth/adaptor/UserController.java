package indi.xezzon.geom.auth.adaptor;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import indi.xezzon.geom.auth.domain.query.UpdateCipherQuery;
import indi.xezzon.geom.auth.service.AuthService;
import indi.xezzon.geom.auth.service.UserService;
import indi.xezzon.tao.exception.ClientException;
import indi.xezzon.tao.logger.LogRecord;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户基础信息相关操作
 * @author xezzon
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private transient final UserService userService;
  private transient final AuthService authService;

  @Autowired
  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  /**
   * 修改密码
   */
  @PatchMapping("/cipher")
  @SaCheckLogin
  public void updateCipher(@RequestBody UpdateCipherQuery query) {
    boolean checked = authService.checkCipher(query.getOldCipher());
    if (!checked) {
      throw new ClientException("密码错误");
    }
    userService.updateCipher(StpUtil.getLoginId(null), query.getNewCipher());
  }

  /**
   * 禁用用户
   * @param userId 用户ID
   * @param activateTime 禁用至
   */
  @PutMapping("/{userId}/activate-time/{activateTime}")
  @LogRecord
  public void forbidUser(
      @PathVariable String userId,
      @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime activateTime
  ) {
    userService.forbidUser(userId, activateTime);
  }
}
