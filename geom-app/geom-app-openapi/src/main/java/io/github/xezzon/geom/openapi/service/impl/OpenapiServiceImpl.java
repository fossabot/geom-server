package io.github.xezzon.geom.openapi.service.impl;

import io.github.xezzon.geom.openapi.repository.wrapper.OpenapiDAO;
import io.github.xezzon.geom.openapi.repository.wrapper.OpenapiInstanceDAO;
import io.github.xezzon.geom.openapi.service.OpenapiService;
import jakarta.inject.Singleton;

/**
 * @author xezzon
 */
@Singleton
public class OpenapiServiceImpl implements OpenapiService {

  protected final transient OpenapiDAO openapiDAO;
  protected final transient OpenapiInstanceDAO openapiInstanceDAO;

  public OpenapiServiceImpl(OpenapiDAO openapiDAO, OpenapiInstanceDAO openapiInstanceDAO) {
    this.openapiDAO = openapiDAO;
    this.openapiInstanceDAO = openapiInstanceDAO;
  }
}
