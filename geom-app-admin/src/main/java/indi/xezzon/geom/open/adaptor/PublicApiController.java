package indi.xezzon.geom.open.adaptor;

import indi.xezzon.geom.open.domain.PublicApi;
import indi.xezzon.geom.open.service.PublicApiService;
import indi.xezzon.tao.retrieval.CommonQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/public-api")
public class PublicApiController {

  private final transient PublicApiService publicApiService;

  @Autowired
  public PublicApiController(PublicApiService publicApiService) {
    this.publicApiService = publicApiService;
  }

  @GetMapping("")
  public Page<PublicApi> list(CommonQuery commonQuery) {
    return publicApiService.list(commonQuery);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody PublicApi publicApi) {
    publicApiService.insert(publicApi);
  }
}
