package io.github.xezzon.geom.openapi.service;

import cn.hutool.core.util.RandomUtil;
import io.github.xezzon.geom.openapi.domain.Openapi;
import io.github.xezzon.geom.openapi.repository.OpenapiRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@MicronautTest
@TestInstance(Lifecycle.PER_CLASS)
class OpenapiServiceTest {

  @Inject
  protected transient OpenapiService service;
  @Inject
  protected transient OpenapiRepository repository;

  @Test
  void addOpenapi() {
    long count = repository.count();

    Openapi openapi = new Openapi();
    openapi.setName(RandomUtil.randomString(6));
    service.addOpenapi(openapi);

    Assertions.assertEquals(count + 1, repository.count());
    Assertions.assertTrue(repository.existsById(openapi.getId()));
  }
}