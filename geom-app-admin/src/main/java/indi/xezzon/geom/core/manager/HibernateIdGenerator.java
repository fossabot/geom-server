package indi.xezzon.geom.core.manager;

import indi.xezzon.tao.manager.IdGenerator;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateIdGenerator implements IdentifierGenerator {

  private final transient IdGenerator idGenerator;

  @Autowired
  public HibernateIdGenerator(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor,
      Object o) throws HibernateException {
    return idGenerator.nextId();
  }
}
