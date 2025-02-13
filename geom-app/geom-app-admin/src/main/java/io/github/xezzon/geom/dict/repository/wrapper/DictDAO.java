package io.github.xezzon.geom.dict.repository.wrapper;

import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.geom.dict.domain.QDict;
import io.github.xezzon.geom.dict.repository.DictRepository;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import io.micronaut.data.annotation.Repository;

/**
 * @author xezzon
 */
@Repository
public class DictDAO extends BaseJpaWrapper<Dict, QDict, DictRepository> {

  protected DictDAO(DictRepository dao) {
    super(dao);
  }

  @Override
  protected QDict getQuery() {
    return QDict.dict;
  }

  @Override
  protected Class<Dict> getBeanClass() {
    return Dict.class;
  }
}
