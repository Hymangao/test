package com.example.sublandlord.presentation.propertydetail;

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
public final class PropertyDetailViewModel_Factory implements Factory<PropertyDetailViewModel> {
  private final Provider<PropertyRepository> propertyRepositoryProvider;

  public PropertyDetailViewModel_Factory(Provider<PropertyRepository> propertyRepositoryProvider) {
    this.propertyRepositoryProvider = propertyRepositoryProvider;
  }

  @Override
  public PropertyDetailViewModel get() {
    return newInstance(propertyRepositoryProvider.get());
  }

  public static PropertyDetailViewModel_Factory create(
      Provider<PropertyRepository> propertyRepositoryProvider) {
    return new PropertyDetailViewModel_Factory(propertyRepositoryProvider);
  }

  public static PropertyDetailViewModel newInstance(PropertyRepository propertyRepository) {
    return new PropertyDetailViewModel(propertyRepository);
  }
}
