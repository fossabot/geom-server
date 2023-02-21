package io.github.xezzon.geom.dict.service.impl;

import io.github.xezzon.geom.dict.dao.wrapper.WrappedDictDAO;
import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.geom.dict.service.DictService;
import io.github.xezzon.tao.domain.TreeNode;
import io.github.xezzon.tao.exception.ClientException;
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
  public List<Dict> list(String tagCode) {
    Dict tag = dictDAO.get()
        .findByTagAndCode("tags", tagCode);
    if (tag == null) {
      throw new ClientException("字典不存在");
    }
    return TreeNode.nest(tag.getId(), -1, dictDAO.get()::findByParentIdIn);
  }

  @Override
  public void add(Dict dict) {
    Dict entity = dictDAO.get().findByTagAndCode(dict.getTag(), dict.getCode());
    if (entity != null) {
      throw new ClientException("字典编码已存在");
    }
    dictDAO.get().save(dict);
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
