package indi.xezzon.geom.auth.domain.convert;

import indi.xezzon.geom.auth.domain.RegisterQuery;
import indi.xezzon.geom.auth.domain.UserDO;
import indi.xezzon.geom.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author xezzon
 */
@Mapper
public interface UserConvert {

  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  User into(UserDO userDO);

  UserDO from(User user);

  User from(RegisterQuery registerQuery);
}
