package indi.xezzon.geom.open.constant;

/**
 * 开放接口类型
 * @author xezzon
 */
public enum PublicApiTypeEnum {
  /**
   * 外部系统调用内部系统
   */
  RPC(),
  /**
   * 内部系统回调外部系统
   */
  WEBHOOK(),
  ;

  PublicApiTypeEnum() {
  }
}
