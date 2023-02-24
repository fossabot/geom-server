package io.github.xezzon.geom.open.service.impl;

import io.github.xezzon.geom.auth.service.AuthService;
import io.github.xezzon.geom.open.dao.wrapper.WrappedPublicApiDAO;
import io.github.xezzon.geom.open.dao.wrapper.WrappedPublicApiInstanceDAO;
import io.github.xezzon.geom.open.domain.PublicApi;
import io.github.xezzon.geom.open.domain.PublicApiInstance;
import io.github.xezzon.geom.open.service.PublicApiService;
import io.github.xezzon.tao.retrieval.CommonQuery;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author xezzon
 */
@Service
public class PublicApiServiceImpl implements PublicApiService {

  private final transient WrappedPublicApiDAO publicApiDAO;
  private final transient WrappedPublicApiInstanceDAO publicApiInstanceDAO;
  private final transient AuthService authService;

  @Autowired
  public PublicApiServiceImpl(
      WrappedPublicApiDAO publicApiDAO,
      WrappedPublicApiInstanceDAO publicApiInstanceDAO,
      AuthService authService
  ) {
    this.publicApiDAO = publicApiDAO;
    this.publicApiInstanceDAO = publicApiInstanceDAO;
    this.authService = authService;
  }

  @Override
  public void insert(PublicApi publicApi) {
    publicApi.setEnabled(true);
    publicApi.setOwnerId(authService.getCurrentGroup().getId());
    publicApiDAO.get().save(publicApi);
  }

  @Override
  public @NotNull Page<PublicApi> list(CommonQuery params) {
    return publicApiDAO.query(params, authService.getCurrentGroup().getId());
  }

  @Override
  public @Nullable PublicApi getById(String apiId) {
    return publicApiDAO.get().getReferenceById(apiId);
  }

  @Override
  public void update(PublicApi publicApi) {
    publicApiDAO.update(publicApi);
  }

  @Override
  public void apply(PublicApiInstance apiInstance) {
    publicApiInstanceDAO.get().save(apiInstance);
  }

  @Override
  public @NotNull Page<PublicApiInstance> listInstance(CommonQuery params) {
    return publicApiInstanceDAO.query(params);
  }

  @Override
  public void updateInstance(PublicApiInstance apiInstance) {
    publicApiInstanceDAO.update(apiInstance);
  }

  @Override
  public void callback(String code, @Nullable Set<String> instanceOwnerId) {
    List<PublicApiInstance> apiInstances =
        publicApiInstanceDAO.listByCode(code, instanceOwnerId);
    apiInstances.parallelStream()
        .forEach(this::callback);
  }

  public void callback(PublicApiInstance apiInstance) {
    // TODO: DO HTTP POST
  }
}
