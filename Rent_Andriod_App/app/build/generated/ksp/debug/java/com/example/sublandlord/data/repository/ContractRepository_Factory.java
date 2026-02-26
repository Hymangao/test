package com.example.sublandlord.data.repository;

import com.example.sublandlord.data.local.dao.LandlordContractDao;
import com.example.sublandlord.data.local.dao.LeaseDao;
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
public final class ContractRepository_Factory implements Factory<ContractRepository> {
  private final Provider<LandlordContractDao> landlordContractDaoProvider;

  private final Provider<LeaseDao> leaseDaoProvider;

  public ContractRepository_Factory(Provider<LandlordContractDao> landlordContractDaoProvider,
      Provider<LeaseDao> leaseDaoProvider) {
    this.landlordContractDaoProvider = landlordContractDaoProvider;
    this.leaseDaoProvider = leaseDaoProvider;
  }

  @Override
  public ContractRepository get() {
    return newInstance(landlordContractDaoProvider.get(), leaseDaoProvider.get());
  }

  public static ContractRepository_Factory create(
      Provider<LandlordContractDao> landlordContractDaoProvider,
      Provider<LeaseDao> leaseDaoProvider) {
    return new ContractRepository_Factory(landlordContractDaoProvider, leaseDaoProvider);
  }

  public static ContractRepository newInstance(LandlordContractDao landlordContractDao,
      LeaseDao leaseDao) {
    return new ContractRepository(landlordContractDao, leaseDao);
  }
}
