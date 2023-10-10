package io.github.xezzon.geom.openapi.domain.query;

import io.github.xezzon.geom.openapi.domain.Openapi;
import io.github.xezzon.geom.trait.IConverter;
import io.github.xezzon.geom.trait.IQuery;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 新增对外接口的请求
 * @author xezzon
 */
@Data
public class AddOpenapiQuery implements IQuery<Openapi> {

  private String name;

  public Openapi to() {
    return AddOpenapiQueryConverter.INSTANCE.convert(this);
  }
}

@Mapper
interface AddOpenapiQueryConverter extends IConverter<AddOpenapiQuery, Openapi> {

  AddOpenapiQueryConverter INSTANCE = Mappers.getMapper(AddOpenapiQueryConverter.class);

  @Mapping(target = "updateTime", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "deleteTime", ignore = true)
  @Mapping(target = "createTime", ignore = true)
  Openapi convert(AddOpenapiQuery query);
}
