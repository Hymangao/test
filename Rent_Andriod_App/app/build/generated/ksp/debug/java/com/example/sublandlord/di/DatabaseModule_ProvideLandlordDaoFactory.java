package com.example.sublandlord.di;

import com.example.sublandlord.data.local.SubLandlordDatabase;
import com.example.sublandlord.data.local.dao.LandlordDao;
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
public final class DatabaseModule_ProvideLandlordDaoFactory implements Factory<LandlordDao> {
  private final Provider<SubLandlordDatabase> databaseProvider;

  public DatabaseModule_ProvideLandlordDaoFactory(Provider<SubLandlordDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LandlordDao get() {
    return provideLandlordDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideLandlordDaoFactory create(
      Provider<SubLandlordDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLandlordDaoFactory(databaseProvider);
  }

  public static LandlordDao provideLandlordDao(SubLandlordDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLandlordDao(database));
  }
}
