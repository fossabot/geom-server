package indi.xezzon.geom.manager;

import indi.xezzon.tao.manager.IdGenerator;
import java.io.Serializable;
import javax.annotation.Resource;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class HibernateIdGenerator implements IdentifierGenerator {

  @Resource
  private IdGenerator idGenerator;

  @Override
  public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor,
      Object o) throws HibernateException {
    return idGenerator.nextId();
  }
}
