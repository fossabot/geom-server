package indi.xezzon.geom.auth.domain.convert;

import indi.xezzon.geom.auth.domain.query.RegisterQuery;
import indi.xezzon.geom.auth.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xezzon
 */
@Mapper
public interface UserConvert {

  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  User from(RegisterQuery registerQuery);
}
