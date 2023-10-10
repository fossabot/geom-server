package io.github.xezzon.geom.openapi.adaptor;

import io.github.xezzon.geom.openapi.domain.query.AddOpenapiQuery;
import io.github.xezzon.geom.openapi.service.OpenapiService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

/**
 * @author xezzon
 */
@Controller("/openapi")
public class OpenapiRestController {

  protected final transient OpenapiService openapiService;

  public OpenapiRestController(OpenapiService openapiService) {
    this.openapiService = openapiService;
  }

  /**
   * 新增对外接口
   */
  @Post()
  public void addOpenapi(@Body AddOpenapiQuery query) {
    openapiService.addOpenapi(query.to());
  }
}
