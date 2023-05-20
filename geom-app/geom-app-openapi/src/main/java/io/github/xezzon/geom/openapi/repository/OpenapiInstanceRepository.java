package io.github.xezzon.geom.openapi.repository;

import io.github.xezzon.geom.openapi.domain.OpenapiInstance;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

/**
 * @author xezzon
 */
@Repository
public interface OpenapiInstanceRepository
    extends JpaRepository<OpenapiInstance, String> {

}
