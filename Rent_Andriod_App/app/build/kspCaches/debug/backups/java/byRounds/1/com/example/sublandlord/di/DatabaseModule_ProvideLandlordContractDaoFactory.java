package com.example.sublandlord.di;

import com.example.sublandlord.data.local.SubLandlordDatabase;
import com.example.sublandlord.data.local.dao.LandlordContractDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideLandlordContractDaoFactory implements Factory<LandlordContractDao> {
  private final Provider<SubLandlordDatabase> databaseProvider;

  public DatabaseModule_ProvideLandlordContractDaoFactory(
      Provider<SubLandlordDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LandlordContractDao get() {
    return provideLandlordContractDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideLandlordContractDaoFactory create(
      Provider<SubLandlordDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLandlordContractDaoFactory(databaseProvider);
  }

  public static LandlordContractDao provideLandlordContractDao(SubLandlordDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLandlordContractDao(database));
  }
}
