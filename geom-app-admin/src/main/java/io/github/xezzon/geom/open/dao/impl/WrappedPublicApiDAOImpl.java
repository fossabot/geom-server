package io.github.xezzon.geom.open.dao.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import io.github.xezzon.geom.open.dao.PublicApiDAO;
import io.github.xezzon.geom.open.dao.wrapper.WrappedPublicApiDAO;
import io.github.xezzon.geom.open.domain.PublicApi;
import io.github.xezzon.geom.open.domain.QPublicApi;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import io.github.xezzon.tao.jpa.JpaUtil;
import io.github.xezzon.tao.retrieval.CommonQuery;
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
