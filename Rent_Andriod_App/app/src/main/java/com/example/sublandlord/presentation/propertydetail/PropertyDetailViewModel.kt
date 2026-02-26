package com.example.sublandlord.presentation.propertydetail

import com.example.sublandlord.data.repository.PropertyRepository
import com.example.sublandlord.data.local.entity.PropertyEntity
import com.example.sublandlord.presentation.base.BaseViewModel
import com.example.sublandlord.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

data class PropertyDetailUiState(
    override val isLoading: Boolean,
    override val error: String?,
    val property: PropertyEntity?,
    val maintenanceOverview: MaintenanceOverview,
    val occupancyOverview: OccupancyOverview
) : UiState

data class MaintenanceOverview(
    val pendingTickets: Int,
    val lastMaintenanceDate: Long?,
    val upcomingSchedule: String?
)

data class OccupancyOverview(
    val occupiedRooms: Int,
    val totalRooms: Int,
    val occupancyRate: Float
)

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : BaseViewModel<PropertyDetailUiState>() {

    override fun createInitialState(): PropertyDetailUiState = PropertyDetailUiState(
        isLoading = false,
        error = null,
        property = null,
        maintenanceOverview = MaintenanceOverview(0, null, null),
        occupancyOverview = OccupancyOverview(0, 0, 0f)
    )

    fun loadProperty(propertyId: String) {
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
            propertyRepository.getPropertyById(propertyId).collect { property ->
                if (property != null) {
                    val mockMaintenance = MaintenanceOverview(
                        pendingTickets = (0..3).random(),
                        lastMaintenanceDate = System.currentTimeMillis() - 86400000L * (5..30).random(),
                        upcomingSchedule = "下周 HVAC 维护"
                    )

                    val mockOccupancy = OccupancyOverview(
                        occupiedRooms = property.totalRooms - (0..2).random(),
                        totalRooms = property.totalRooms,
                        occupancyRate = if (property.totalRooms == 0) 0f else
                            ((property.totalRooms - 1).toFloat() / property.totalRooms.toFloat())
                    )

                    updateState(
                        getCurrentState().copy(
                            isLoading = false,
                            error = null,
                            property = property,
                            maintenanceOverview = mockMaintenance,
                            occupancyOverview = mockOccupancy
                        )
                    )
                } else {
                    updateState(
                        getCurrentState().copy(
                            isLoading = false,
                            error = "未找到房屋详情"
                        )
                    )
                }
            }
        }
    }
}
