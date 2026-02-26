package com.example.sublandlord.presentation.properties;

import com.example.sublandlord.data.repository.PropertyRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class PropertiesViewModel_Factory implements Factory<PropertiesViewModel> {
  private final Provider<PropertyRepository> propertyRepositoryProvider;

  public PropertiesViewModel_Factory(Provider<PropertyRepository> propertyRepositoryProvider) {
    this.propertyRepositoryProvider = propertyRepositoryProvider;
  }

  @Override
  public PropertiesViewModel get() {
    return newInstance(propertyRepositoryProvider.get());
  }

  public static PropertiesViewModel_Factory create(
      Provider<PropertyRepository> propertyRepositoryProvider) {
    return new PropertiesViewModel_Factory(propertyRepositoryProvider);
  }

  public static PropertiesViewModel newInstance(PropertyRepository propertyRepository) {
    return new PropertiesViewModel(propertyRepository);
  }
}
