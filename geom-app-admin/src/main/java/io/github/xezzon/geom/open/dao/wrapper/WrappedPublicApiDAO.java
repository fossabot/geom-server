package io.github.xezzon.geom.open.dao.wrapper;

import io.github.xezzon.geom.open.dao.PublicApiDAO;
import io.github.xezzon.geom.open.domain.PublicApi;
import io.github.xezzon.tao.jpa.JpaWrapper;
import io.github.xezzon.tao.retrieval.CommonQuery;
import org.springframework.data.domain.Page;

/**
 * @author xezzon
 */
public interface WrappedPublicApiDAO extends JpaWrapper<PublicApi, PublicApiDAO> {

  /**
   * 通用查询接口（含分页）
   * @param params 查询参数
   * @param ownerId 所有者ID
   * @return 带分页的结果
   */
  Page<PublicApi> query(CommonQuery params, String ownerId);
}
