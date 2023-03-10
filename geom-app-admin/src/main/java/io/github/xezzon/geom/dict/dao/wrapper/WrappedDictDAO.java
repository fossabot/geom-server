package io.github.xezzon.geom.dict.dao.wrapper;

import io.github.xezzon.geom.dict.dao.DictDAO;
import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.tao.jpa.JpaWrapper;

/**
 * @author xezzon
 */
public interface WrappedDictDAO extends JpaWrapper<Dict, DictDAO> {

}
