package com.example.sublandlord.di;

import com.example.sublandlord.data.remote.api.SubLandlordApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class NetworkModule_ProvideSubLandlordApiFactory implements Factory<SubLandlordApi> {
  @Override
  public SubLandlordApi get() {
    return provideSubLandlordApi();
  }

  public static NetworkModule_ProvideSubLandlordApiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SubLandlordApi provideSubLandlordApi() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideSubLandlordApi());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideSubLandlordApiFactory INSTANCE = new NetworkModule_ProvideSubLandlordApiFactory();
  }
}
