package com.example.sublandlord.presentation.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<PropertyRepository> propertyRepositoryProvider;

  public HomeViewModel_Factory(Provider<PropertyRepository> propertyRepositoryProvider) {
    this.propertyRepositoryProvider = propertyRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(propertyRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<PropertyRepository> propertyRepositoryProvider) {
    return new HomeViewModel_Factory(propertyRepositoryProvider);
  }

  public static HomeViewModel newInstance(PropertyRepository propertyRepository) {
    return new HomeViewModel(propertyRepository);
  }
}
