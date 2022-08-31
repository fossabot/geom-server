package indi.xezzon.geom.manager;

import cn.hutool.core.util.IdUtil;
import indi.xezzon.tao.manager.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "bean.id-generator", havingValue = "snowflake")
public class SnowflakeIdGenerator implements IdGenerator {

  @Autowired
  public SnowflakeIdGenerator() {
  }

  @Override
  public String nextId() {
    return IdUtil.getSnowflakeNextIdStr();
  }
}
