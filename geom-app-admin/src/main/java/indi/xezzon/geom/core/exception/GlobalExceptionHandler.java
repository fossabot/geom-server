package indi.xezzon.geom.core.exception;

import indi.xezzon.tao.domain.Result;
import indi.xezzon.tao.exception.ServerException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 参数校验失败
   */
  @ExceptionHandler(BindException.class)
  public Result<Void> handleBindException(BindException e) {
    IllegalParameterException ipe = new IllegalParameterException(e);
    return Result.fail(ipe.getCode(), ipe.getMessage());
  }

  /**
   * 拦截未知异常
   */
  @ExceptionHandler(Exception.class)
  public Result<Void> handleRuntimeException(RuntimeException e) {
    ServerException se = new ServerException(e.getMessage(), e);
    return Result.fail(se.getCode(), "异常响应的操作，请联系客服。");
  }
}
