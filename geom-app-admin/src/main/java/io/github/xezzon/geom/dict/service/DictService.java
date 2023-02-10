package io.github.xezzon.geom.dict.service;

import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.tao.dict.IDictService;
import java.util.List;

/**
 * @author xezzon
 */
public interface DictService extends IDictService {

  /**
   * 列出所有字典目
   * @return 字典目
   */
  List<Dict> listTag();

  /**
   * 列表
   * @param tag 字典目
   * @return 字典
   */
  List<Dict> list(String tag);

  /**
   * 新增
   * @param dict 字典
   */
  void add(Dict dict);

  /**
   * 修改
   * @param dict 字典
   */
  void modify(Dict dict);

  /**
   * 修改字典目
   * 需要同步修改该字典目下的字典
   * @param dict 字典
   */
  void modifyTag(Dict dict);

  /**
   * 级联删除
   * @param id 字典主键
   */
  void remove(String id);
}
