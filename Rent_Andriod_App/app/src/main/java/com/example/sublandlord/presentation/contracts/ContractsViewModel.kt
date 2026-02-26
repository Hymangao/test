package com.example.sublandlord.presentation.contracts

import com.example.sublandlord.data.local.entity.ContractStatus
import com.example.sublandlord.data.local.entity.LandlordContractEntity
import com.example.sublandlord.data.local.entity.LeaseEntity
import com.example.sublandlord.data.repository.ContractRepository
import com.example.sublandlord.presentation.base.BaseViewModel
import com.example.sublandlord.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

enum class ContractsTab {
    LANDLORD,
    LEASE
}

data class ContractSummary(
    val id: String,
    val propertyLabel: String,
    val counterpartLabel: String,
    val monthlyRent: Float,
    val startDate: Long,
    val endDate: Long,
    val status: ContractStatus,
    val typeLabel: String
)

data class ContractFormInput(
    val contractType: ContractsTab,
    val propertyId: String,
    val counterpartId: String,
    val startDate: Long,
    val endDate: Long,
    val monthlyRent: Float
)

data class ContractsUiState(
    override val isLoading: Boolean,
    override val error: String?,
    val selectedTab: ContractsTab,
    val landlordContracts: List<ContractSummary>,
    val leases: List<ContractSummary>,
    val submissionMessage: String?
) : UiState

@HiltViewModel
class ContractsViewModel @Inject constructor(
    private val contractRepository: ContractRepository
) : BaseViewModel<ContractsUiState>() {

    override fun createInitialState(): ContractsUiState = ContractsUiState(
        isLoading = true,
        error = null,
        selectedTab = ContractsTab.LANDLORD,
        landlordContracts = emptyList(),
        leases = emptyList(),
        submissionMessage = null
    )

    init {
        observeContracts()
    }

    private fun observeContracts() {
        launch(
            onError = { error ->
                updateState(
                    getCurrentState().copy(
                        isLoading = false,
                        error = error.message
                    )
                )
            }
        ) {
            combine(
                contractRepository.getLandlordContracts(),
                contractRepository.getLeases()
            ) { landlordContracts, leases ->
                landlordContracts to leases
            }.collect { (landlordContracts, leases) ->
                updateState(
                    getCurrentState().copy(
                        isLoading = false,
                        error = null,
                        landlordContracts = landlordContracts.map { it.toSummary() },
                        leases = leases.map { it.toLeaseSummary() }
                    )
                )
            }
        }
    }

    fun selectTab(tab: ContractsTab) {
        updateState(
            getCurrentState().copy(selectedTab = tab)
        )
    }

    fun submitContractDraft(input: ContractFormInput) {
        val message = when (input.contractType) {
            ContractsTab.LANDLORD -> "房东合同草稿已保存"
            ContractsTab.LEASE -> "租约草稿已保存"
        }
        updateState(
            getCurrentState().copy(
                submissionMessage = message
            )
        )
    }

    fun clearSubmissionMessage() {
        if (getCurrentState().submissionMessage != null) {
            updateState(getCurrentState().copy(submissionMessage = null))
        }
    }

    private fun LandlordContractEntity.toSummary(): ContractSummary = ContractSummary(
        id = id,
        propertyLabel = propertyId,
        counterpartLabel = landlordId,
        monthlyRent = monthlyRent,
        startDate = startDate,
        endDate = endDate,
        status = status,
        typeLabel = "房东合同"
    )

    private fun LeaseEntity.toLeaseSummary(): ContractSummary = ContractSummary(
        id = id,
        propertyLabel = roomId,
        counterpartLabel = tenantId,
        monthlyRent = monthlyRent,
        startDate = startDate,
        endDate = endDate,
        status = status,
        typeLabel = "租约"
    )
}
