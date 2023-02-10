package io.github.xezzon.geom.dict.adaptor;

import io.github.xezzon.geom.dict.service.DictService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/dict")
public class DictController {

  @Resource
  private transient DictService dictService;
}
