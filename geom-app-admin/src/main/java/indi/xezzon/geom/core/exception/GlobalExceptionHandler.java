package indi.xezzon.geom.core.exception;

import indi.xezzon.tao.domain.Result;
import indi.xezzon.tao.exception.ClientException;
import indi.xezzon.tao.exception.ServerException;
import indi.xezzon.tao.exception.ThirdPartyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xezzon
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String ERROR_MESSAGE = "无法响应您的操作，请稍后重试或联系客服。";

  /**
   * 参数校验失败
   */
  @ExceptionHandler(BindException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public Result<Void> handleBindException(BindException e) {
    IllegalParameterException ipe = new IllegalParameterException(e);
    return Result.fail(ipe.getCode(), ipe.getMessage());
  }

  /**
   * 客户端异常
   */
  @ExceptionHandler(ClientException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public Result<Void> handleClientException(ClientException e) {
    return Result.fail(e.getCode(), e.getMessage());
  }

  /**
   * 预期的服务端异常
   */
  @ExceptionHandler(ServerException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<Void> handleServerException(ServerException e) {
    return Result.fail(e.getCode(), ERROR_MESSAGE);
  }

  /**
   * 第三方服务异常
   */
  @ExceptionHandler(ThirdPartyException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<Void> handleThirdPartyException(ThirdPartyException e) {
    return Result.fail(e.getCode(), ERROR_MESSAGE);
  }

  /**
   * 拦截未知异常
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Result<Void> handleRuntimeException(RuntimeException e) {
    ServerException se = new ServerException(e.getMessage(), e);
    return Result.fail(se.getCode(), ERROR_MESSAGE);
  }
}
