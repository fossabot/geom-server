package indi.xezzon.geom.domain.convert;

import indi.xezzon.geom.domain.User;
import indi.xezzon.geom.domain.system.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  User into(UserDO userDO);

  UserDO into(User user);
}
