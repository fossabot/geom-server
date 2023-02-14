package io.github.xezzon.geom.dict.service.impl;

import io.github.xezzon.geom.dict.dao.wrapper.WrappedDictDAO;
import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.geom.dict.service.DictService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class DictServiceImpl implements DictService {

  private final transient WrappedDictDAO dictDAO;

  public DictServiceImpl(WrappedDictDAO wrappedDictDAO) {
    this.dictDAO = wrappedDictDAO;
  }

  @Override
  public List<Dict> findByTag(String tag) {
    return dictDAO.get().findByTag(tag);
  }

  @Override
  public List<Dict> listTag() {
    return this.findByTag("tags");
  }

  @Override
  public List<Dict> list(String tag) {
    return null;
  }

  @Override
  public void add(Dict dict) {

  }

  @Override
  public void modify(Dict dict) {

  }

  @Override
  public void modifyTag(Dict dict) {

  }

  @Override
  public void remove(String id) {

  }
}
