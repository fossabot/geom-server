package io.github.xezzon.geom.core.manager;

import io.github.xezzon.tao.manager.IdGenerator;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xezzon
 */
@Component
public class HibernateIdGenerator implements IdentifierGenerator {

  public static final String GENERATOR_NAME = "id-generator";
  public static final String GENERATOR_STRATEGY
      = "io.github.xezzon.geom.core.manager.HibernateIdGenerator";
  private final transient IdGenerator idGenerator;

  @Autowired
  public HibernateIdGenerator(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  @Override
  public Serializable generate(
      SharedSessionContractImplementor sharedSessionContractImplementor,
      Object o
  ) throws HibernateException {
    Serializable originId = sharedSessionContractImplementor
        .getEntityPersister(null, o)
        .getClassMetadata()
        .getIdentifier(o, sharedSessionContractImplementor);
    if (originId != null) {
      return originId;
    }
    return idGenerator.nextId();
  }
}
