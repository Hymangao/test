package com.example.sublandlord.data.repository;

import com.example.sublandlord.data.local.dao.PropertyDao;
import com.example.sublandlord.data.remote.api.SubLandlordApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class PropertyRepository_Factory implements Factory<PropertyRepository> {
  private final Provider<PropertyDao> propertyDaoProvider;

  private final Provider<SubLandlordApi> apiProvider;

  public PropertyRepository_Factory(Provider<PropertyDao> propertyDaoProvider,
      Provider<SubLandlordApi> apiProvider) {
    this.propertyDaoProvider = propertyDaoProvider;
    this.apiProvider = apiProvider;
  }

  @Override
  public PropertyRepository get() {
    return newInstance(propertyDaoProvider.get(), apiProvider.get());
  }

  public static PropertyRepository_Factory create(Provider<PropertyDao> propertyDaoProvider,
      Provider<SubLandlordApi> apiProvider) {
    return new PropertyRepository_Factory(propertyDaoProvider, apiProvider);
  }

  public static PropertyRepository newInstance(PropertyDao propertyDao, SubLandlordApi api) {
    return new PropertyRepository(propertyDao, api);
  }
}
