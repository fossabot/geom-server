package io.github.xezzon.geom.dict.dao.impl;

import io.github.xezzon.geom.dict.dao.DictDAO;
import io.github.xezzon.geom.dict.dao.wrapper.WrappedDictDAO;
import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.geom.dict.domain.QDict;
import io.github.xezzon.tao.jpa.BaseJpaWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public class WrappedDictDAOImpl
    extends BaseJpaWrapper<Dict, QDict, DictDAO>
    implements WrappedDictDAO {

  protected WrappedDictDAOImpl(DictDAO dao) {
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
