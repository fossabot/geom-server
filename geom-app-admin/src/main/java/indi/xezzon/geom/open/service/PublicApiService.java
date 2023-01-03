package indi.xezzon.geom.open.service;

import indi.xezzon.geom.open.domain.PublicApi;
import indi.xezzon.geom.open.domain.PublicApiInstance;
import indi.xezzon.tao.retrieval.CommonQuery;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;

/**
 * @author xezzon
 */
public interface PublicApiService {

  /**
   * 新增开放接口
   * @param publicApi 开放接口信息
   */
  void insert(PublicApi publicApi);

  /**
   * 分页查询开放接口
   * @param params 通用查询参数
   * @return 带分页的开放接口信息列表
   */
  @NotNull
  Page<PublicApi> list(CommonQuery params);

  /**
   * 通过主键查询
   * @param apiId 开放接口主键
   * @return 开放接口信息
   */
  @Nullable
  PublicApi getById(String apiId);

  /**
   * 修改开放接口
   * @param publicApi 开放接口信息
   */
  void update(PublicApi publicApi);

  /**
   * 申请开放接口
   * @param apiInstance 开放接口实例
   */
  void apply(PublicApiInstance apiInstance);

  /**
   * 分页查询当前用户组订阅的开放接口
   * @param params 通用查询参数
   * @return 带分页的开放接口实例列表
   */
  @NotNull
  Page<PublicApiInstance> listInstance(CommonQuery params);

  /**
   * 修改开放接口实例
   * @param apiInstance 开放接口实例
   */
  void updateInstance(PublicApiInstance apiInstance);

  /**
   * 触发 webhook 回调
   * @param code 调用识别码
   * @param instanceOwnerId 接受通知的用户组。为 null 则会向所有订阅该接口的用户组广播
   */
  void callback(String code, @Nullable Set<String> instanceOwnerId);
}
