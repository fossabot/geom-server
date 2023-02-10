package indi.xezzon.geom.open.dao.impl;

import com.querydsl.jpa.impl.JPAQuery;
import indi.xezzon.geom.open.dao.PublicApiInstanceDAO;
import indi.xezzon.geom.open.dao.wrapper.WrappedPublicApiInstanceDAO;
import indi.xezzon.geom.open.domain.PublicApiInstance;
import indi.xezzon.geom.open.domain.QPublicApi;
import indi.xezzon.geom.open.domain.QPublicApiInstance;
import indi.xezzon.tao.jpa.BaseJpaWrapper;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public class WrappedPublicApiInstanceDAOImpl
    extends BaseJpaWrapper<PublicApiInstance, QPublicApiInstance, PublicApiInstanceDAO>
    implements WrappedPublicApiInstanceDAO {

  protected WrappedPublicApiInstanceDAOImpl(PublicApiInstanceDAO dao) {
    super(dao);
  }

  @Override
  protected QPublicApiInstance getQuery() {
    return QPublicApiInstance.publicApiInstance;
  }

  @Override
  protected Class<PublicApiInstance> getBeanClass() {
    return PublicApiInstance.class;
  }

  @Override
  public List<PublicApiInstance> listByCode(String code, Collection<String> ownerIds) {
    if (ownerIds != null && ownerIds.isEmpty()) {
      return Collections.emptyList();
    }
    final QPublicApi qPublicApi = QPublicApi.publicApi;
    JPAQuery<PublicApiInstance> queryClause = queryFactory.selectFrom(this.getQuery())
        .innerJoin(qPublicApi).on(this.getQuery().apiId.eq(qPublicApi.id))
        .where(qPublicApi.code.eq(code)
            .and(qPublicApi.enabled.eq(true))
            .and(this.getQuery().enabled.eq(true))
        );
    if (ownerIds != null) {
      queryClause.where(this.getQuery().ownerId.in(ownerIds));
    }
    return queryClause.fetch();
  }
}
