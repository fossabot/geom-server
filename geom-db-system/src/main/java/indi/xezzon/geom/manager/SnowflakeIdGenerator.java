package indi.xezzon.geom.manager;

import cn.hutool.core.util.IdUtil;
import indi.xezzon.tao.manager.IdGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "bean.id-generator", havingValue = "snowflake")
public class SnowflakeIdGenerator implements IdGenerator {

  @Override
  public String nextId() {
    return IdUtil.getSnowflakeNextIdStr();
  }
}
