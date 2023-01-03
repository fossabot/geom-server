package indi.xezzon.geom.open.dao.wrapper;

import indi.xezzon.geom.external.jpa.JpaWrapper;
import indi.xezzon.geom.open.dao.PublicApiDAO;
import indi.xezzon.geom.open.domain.PublicApi;
import indi.xezzon.tao.retrieval.CommonQuery;
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
