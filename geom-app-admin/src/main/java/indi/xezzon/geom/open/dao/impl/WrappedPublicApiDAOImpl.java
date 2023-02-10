package indi.xezzon.geom.open.dao.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import indi.xezzon.geom.open.dao.PublicApiDAO;
import indi.xezzon.geom.open.dao.wrapper.WrappedPublicApiDAO;
import indi.xezzon.geom.open.domain.PublicApi;
import indi.xezzon.geom.open.domain.QPublicApi;
import indi.xezzon.tao.jpa.BaseJpaWrapper;
import indi.xezzon.tao.jpa.JpaUtil;
import indi.xezzon.tao.retrieval.CommonQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public class WrappedPublicApiDAOImpl
    extends BaseJpaWrapper<PublicApi, QPublicApi, PublicApiDAO>
    implements WrappedPublicApiDAO {

  protected WrappedPublicApiDAOImpl(PublicApiDAO dao) {
    super(dao);
  }

  @Override
  protected QPublicApi getQuery() {
    return QPublicApi.publicApi;
  }

  @Override
  protected Class<PublicApi> getBeanClass() {
    return PublicApi.class;
  }

  @Override
  public Page<PublicApi> query(CommonQuery params, String ownerId) {
    BooleanExpression queryClause =
        JpaUtil.getQueryClause(params, this.getQuery(), this.getBeanClass());
    queryClause.and(this.getQuery().ownerId.eq(ownerId));
    Pageable pageable = JpaUtil.getPageable(params);
    return this.get().findAll(queryClause, pageable);
  }
}
