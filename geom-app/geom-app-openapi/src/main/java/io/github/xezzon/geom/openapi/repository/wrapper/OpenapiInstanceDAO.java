package io.github.xezzon.geom.openapi.repository.wrapper;

import io.github.xezzon.geom.openapi.domain.OpenapiInstance;
import io.github.xezzon.geom.openapi.domain.QOpenapiInstance;
import io.github.xezzon.geom.openapi.repository.OpenapiInstanceRepository;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import io.micronaut.data.annotation.Repository;

/**
 * @author xezzon
 */
@Repository
public class OpenapiInstanceDAO
    extends BaseJpaWrapper<OpenapiInstance, QOpenapiInstance, OpenapiInstanceRepository> {

  protected OpenapiInstanceDAO(OpenapiInstanceRepository repository) {
    super(repository);
  }

  @Override
  protected QOpenapiInstance getQuery() {
    return QOpenapiInstance.openapiInstance;
  }

  @Override
  protected Class<OpenapiInstance> getBeanClass() {
    return OpenapiInstance.class;
  }
}
