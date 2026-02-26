package com.example.sublandlord.presentation.contracts;

import com.example.sublandlord.data.repository.ContractRepository;
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
public final class ContractsViewModel_Factory implements Factory<ContractsViewModel> {
  private final Provider<ContractRepository> contractRepositoryProvider;

  public ContractsViewModel_Factory(Provider<ContractRepository> contractRepositoryProvider) {
    this.contractRepositoryProvider = contractRepositoryProvider;
  }

  @Override
  public ContractsViewModel get() {
    return newInstance(contractRepositoryProvider.get());
  }

  public static ContractsViewModel_Factory create(
      Provider<ContractRepository> contractRepositoryProvider) {
    return new ContractsViewModel_Factory(contractRepositoryProvider);
  }

  public static ContractsViewModel newInstance(ContractRepository contractRepository) {
    return new ContractsViewModel(contractRepository);
  }
}
