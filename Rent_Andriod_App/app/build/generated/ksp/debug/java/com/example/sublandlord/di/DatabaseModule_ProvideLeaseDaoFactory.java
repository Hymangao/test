package com.example.sublandlord.di;

import com.example.sublandlord.data.local.SubLandlordDatabase;
import com.example.sublandlord.data.local.dao.LeaseDao;
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
public final class DatabaseModule_ProvideLeaseDaoFactory implements Factory<LeaseDao> {
  private final Provider<SubLandlordDatabase> databaseProvider;

  public DatabaseModule_ProvideLeaseDaoFactory(Provider<SubLandlordDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LeaseDao get() {
    return provideLeaseDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideLeaseDaoFactory create(
      Provider<SubLandlordDatabase> databaseProvider) {
    return new DatabaseModule_ProvideLeaseDaoFactory(databaseProvider);
  }

  public static LeaseDao provideLeaseDao(SubLandlordDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLeaseDao(database));
  }
}
