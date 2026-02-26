package com.example.sublandlord.presentation.home

import com.example.sublandlord.data.local.entity.PropertyStatus
import com.example.sublandlord.data.repository.PropertyRepository
import com.example.sublandlord.presentation.base.BaseViewModel
import com.example.sublandlord.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

data class HomeActivityItem(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: Long
)

data class HomeUiState(
    override val isLoading: Boolean,
    override val error: String?,
    val totalProperties: Int,
    val rentedCount: Int,
    val vacantCount: Int,
    val monthlyIncome: Long,
    val expiringContractsCount: Int,
    val monthlyIncomeTrend: List<Long>,
    val recentActivities: List<HomeActivityItem>
) : UiState

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : BaseViewModel<HomeUiState>() {

    override fun createInitialState(): HomeUiState = HomeUiState(
        isLoading = false,
        error = null,
        totalProperties = 0,
        rentedCount = 0,
        vacantCount = 0,
        monthlyIncome = 0L,
        expiringContractsCount = 0,
        monthlyIncomeTrend = emptyList(),
        recentActivities = emptyList()
    )

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        updateState(
            getCurrentState().copy(
                isLoading = true,
                error = null
            )
        )

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
            val properties = propertyRepository.getAllProperties().first()

            val total = properties.size
            val rented = properties.count { it.status == PropertyStatus.RENTED }
            val vacant = properties.count { it.status == PropertyStatus.VACANT }

            val mockMonthlyIncome = rented * 3000L
            val mockTrend = listOf(8000L, 9500L, 12000L, 11000L, 13000L, mockMonthlyIncome)

            val now = System.currentTimeMillis()
            val mockActivities = listOf(
                HomeActivityItem(
                    id = "1",
                    title = "新增房屋",
                    description = "刚刚添加了 1 套房屋",
                    timestamp = now
                ),
                HomeActivityItem(
                    id = "2",
                    title = "租约即将到期",
                    description = "有 2 个租约将在 30 天内到期",
                    timestamp = now - 3600_000L
                )
            )

            updateState(
                getCurrentState().copy(
                    isLoading = false,
                    error = null,
                    totalProperties = total,
                    rentedCount = rented,
                    vacantCount = vacant,
                    monthlyIncome = mockMonthlyIncome,
                    expiringContractsCount = 2,
                    monthlyIncomeTrend = mockTrend,
                    recentActivities = mockActivities
                )
            )
        }
    }
}
