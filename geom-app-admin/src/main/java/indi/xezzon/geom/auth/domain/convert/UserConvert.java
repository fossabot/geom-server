package indi.xezzon.geom.auth.domain.convert;

import indi.xezzon.geom.auth.domain.query.RegisterQuery;
import indi.xezzon.geom.auth.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author xezzon
 */
@Mapper
public interface UserConvert {

  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  /**
   * 转换注册接口参数
   * @param registerQuery 注册接口参数
   * @return 用户模型
   */
  @Mapping(target = "updateTime", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createTime", ignore = true)
  @Mapping(target = "activateTime", ignore = true)
  @Mapping(target = "cipher", ignore = true)
  @Mapping(target = "plaintext", source = "cipher")
  User from(RegisterQuery registerQuery);
}
