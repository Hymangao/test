package com.example.sublandlord.di;

import com.example.sublandlord.data.local.SubLandlordDatabase;
import com.example.sublandlord.data.local.dao.RoomDao;
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
public final class DatabaseModule_ProvideRoomDaoFactory implements Factory<RoomDao> {
  private final Provider<SubLandlordDatabase> databaseProvider;

  public DatabaseModule_ProvideRoomDaoFactory(Provider<SubLandlordDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomDao get() {
    return provideRoomDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRoomDaoFactory create(
      Provider<SubLandlordDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRoomDaoFactory(databaseProvider);
  }

  public static RoomDao provideRoomDao(SubLandlordDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoomDao(database));
  }
}
