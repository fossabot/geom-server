package io.github.xezzon.geom.dict.adaptor;

import io.github.xezzon.geom.dict.domain.Dict;
import io.github.xezzon.geom.dict.service.DictService;
import java.util.List;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/dict")
public class DictController {

  @Resource
  private transient DictService dictService;

  @GetMapping("/tags")
  public List<Dict> listTags() {
    return dictService.listTag();
  }

  @GetMapping("/{tag}")
  public List<Dict> list(@PathVariable String tag) {
    return dictService.list(tag);
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public void add(@RequestBody Dict dict) {
    dictService.add(dict);
  }
}
