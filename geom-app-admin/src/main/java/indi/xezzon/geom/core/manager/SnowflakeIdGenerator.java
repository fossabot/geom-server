package indi.xezzon.geom.core.manager;

import cn.hutool.core.util.IdUtil;
import indi.xezzon.geom.core.constant.SpringConstants;
import indi.xezzon.tao.manager.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author xezzon
 */
@Component
@ConditionalOnProperty(
    prefix = SpringConstants.BEAN_PREFIX,
    name = SpringConstants.ID_GENERATOR, 
    havingValue = "snowflake"
)
public class SnowflakeIdGenerator implements IdGenerator {

  @Autowired
  public SnowflakeIdGenerator() {
  }

  @Override
  public String nextId() {
    return IdUtil.getSnowflakeNextIdStr();
  }
}
