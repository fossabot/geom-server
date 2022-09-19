package indi.xezzon.geom.core.domain;

import java.util.Collection;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据集抽象类 用于定义初始化数据（含正式数据及测试数据）的抽象类
 * @author xezzon
 */
public abstract class AbstractDataset<T> {

  protected final Collection<T> dataset;
  private final JpaRepository<T, ?> repository;

  protected AbstractDataset(Collection<T> dataset, JpaRepository<T, ?> repository) {
    this.dataset = dataset;
    this.repository = repository;
  }

  /**
   * 初始化数据集
   */
  @PostConstruct
  public void initialize() {
    if (dataset.isEmpty()) {
      return;
    }
    repository.saveAll(dataset);
  }

  /**
   * 从数据集中取一条数据
   * @param predicate 筛选条件
   * @return 数据集中符合条件的任一数据 若没有则返回null
   */
  public T find(Predicate<T> predicate) {
    return dataset.stream()
        .filter(predicate)
        .findAny()
        .orElse(null);
  }
}
