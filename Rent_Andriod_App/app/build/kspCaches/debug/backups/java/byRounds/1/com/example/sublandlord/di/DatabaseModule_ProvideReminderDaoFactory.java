package com.example.sublandlord.di;

import com.example.sublandlord.data.local.SubLandlordDatabase;
import com.example.sublandlord.data.local.dao.ReminderDao;
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
public final class DatabaseModule_ProvideReminderDaoFactory implements Factory<ReminderDao> {
  private final Provider<SubLandlordDatabase> databaseProvider;

  public DatabaseModule_ProvideReminderDaoFactory(Provider<SubLandlordDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ReminderDao get() {
    return provideReminderDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideReminderDaoFactory create(
      Provider<SubLandlordDatabase> databaseProvider) {
    return new DatabaseModule_ProvideReminderDaoFactory(databaseProvider);
  }

  public static ReminderDao provideReminderDao(SubLandlordDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideReminderDao(database));
  }
}
