package io.github.xezzon.geom.core.config;

import static io.github.xezzon.geom.core.constant.SpringConstants.BEAN_PREFIX;
import static io.github.xezzon.geom.core.constant.SpringConstants.SA_TOKEN_DAO;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.dao.SaTokenDaoDefaultImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author xezzon
 */
@Configuration
public class SaTokenConfig {
}

/**
 * 动态选择会话序列化方式
 * @deprecated 现在 sa-token 不支持动态选择，所以自己实现一下
 */
@Component
@ConditionalOnProperty(
    prefix = BEAN_PREFIX,
    name = SA_TOKEN_DAO,
    havingValue = "default",
    matchIfMissing = true
)
@Deprecated
class SaTokenDefaultDaoConfig implements CommandLineRunner {

  @Override
  public void run(String... args) {
    SaManager.setSaTokenDao(new SaTokenDaoDefaultImpl());
  }
}
