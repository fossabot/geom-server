package io.github.xezzon.geom.open.dao.wrapper;

import io.github.xezzon.geom.open.dao.PublicApiInstanceDAO;
import io.github.xezzon.geom.open.domain.PublicApiInstance;
import io.github.xezzon.tao.jpa.JpaWrapper;
import java.util.Collection;
import java.util.List;

/**
 * @author xezzon
 */
public interface WrappedPublicApiInstanceDAO
    extends JpaWrapper<PublicApiInstance, PublicApiInstanceDAO> {

  /**
   * 获取指定编码的实例
   * @param code 接口编码
   * @param ownerIds 所属用户
   * @return 接口实例
   */
  List<PublicApiInstance> listByCode(String code, Collection<String> ownerIds);
}
