package com.example.sublandlord.presentation.properties

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.sublandlord.data.local.entity.PropertyEntity
import com.example.sublandlord.data.local.entity.PropertyType
import com.example.sublandlord.data.local.entity.PropertyStatus
import com.example.sublandlord.data.repository.PropertyRepository
import com.example.sublandlord.presentation.base.BaseViewModel
import com.example.sublandlord.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


data class PropertiesUiState(
    override val isLoading: Boolean,
    override val error: String?,
    val properties: List<PropertyEntity>,
    val selectedProperty: PropertyEntity?,
    val filterStatus: PropertyStatus?,
    val searchQuery: String
) : UiState

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : BaseViewModel<PropertiesUiState>() {

    var searchQuery by mutableStateOf("")
        private set

    var filterStatus by mutableStateOf<PropertyStatus?>(null)
        private set

    init {
        loadProperties()
    }

    override fun createInitialState(): PropertiesUiState = PropertiesUiState(
        isLoading = false,
        error = null,
        properties = emptyList(),
        selectedProperty = null,
        filterStatus = null,
        searchQuery = ""
    )

    fun loadProperties() {
        val currentFilterStatus = filterStatus
        val currentSearchQuery = searchQuery

        updateState(
            getCurrentState().copy(
                isLoading = true,
                error = null,
                filterStatus = currentFilterStatus,
                searchQuery = currentSearchQuery
            )
        )

        launch(
            onError = { error ->
                updateState(
                    getCurrentState().copy(
                        isLoading = false,
                        error = error.message,
                        filterStatus = currentFilterStatus,
                        searchQuery = currentSearchQuery
                    )
                )
            }
        ) {
            val flow = when {
                currentFilterStatus != null -> propertyRepository.getPropertiesByStatus(currentFilterStatus)
                currentSearchQuery.isNotEmpty() -> propertyRepository.getAllProperties()
                else -> propertyRepository.getAllProperties()
            }

            var properties: List<PropertyEntity> = emptyList()
            flow.catch { error ->
                updateState(
                    getCurrentState().copy(
                        isLoading = false,
                        error = error.message,
                        filterStatus = currentFilterStatus,
                        searchQuery = currentSearchQuery
                    )
                )
            }.collect { list ->
                properties = if (currentSearchQuery.isNotEmpty()) {
                    list.filter { property ->
                        property.name.contains(currentSearchQuery, ignoreCase = true) ||
                            property.address.contains(currentSearchQuery, ignoreCase = true)
                    }
                } else {
                    list
                }
                updateState(
                    getCurrentState().copy(
                        isLoading = false,
                        properties = properties,
                        error = null,
                        filterStatus = currentFilterStatus,
                        searchQuery = currentSearchQuery
                    )
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
        loadProperties()
    }

    fun onFilterStatusChanged(status: PropertyStatus?) {
        filterStatus = status
        loadProperties()
    }

    fun onPropertySelected(property: PropertyEntity) {
        updateState(
            getCurrentState().copy(
                selectedProperty = property
            )
        )
    }

    fun clearPropertySelection() {
        updateState(
            getCurrentState().copy(
                selectedProperty = null
            )
        )
    }

    suspend fun createProperty(
        name: String,
        address: String,
        city: String,
        district: String,
        type: PropertyType,
        area: Float,
        floor: String,
        totalRooms: Int
    ) {
        updateState(
            getCurrentState().copy(
                isLoading = true,
                error = null
            )
        )

        try {
            val property = PropertyEntity(
                id = generateId(),
                name = name,
                address = address,
                city = city,
                district = district,
                latitude = null,
                longitude = null,
                type = type,
                area = area,
                floor = floor,
                totalRooms = totalRooms,
                status = PropertyStatus.VACANT,
                images = "[]",
                amenities = "[]",
                description = "",
                purchasePrice = null,
                decorationCost = null,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            propertyRepository.createProperty(property)
            loadProperties()
        } catch (e: Exception) {
            updateState(
                getCurrentState().copy(
                    isLoading = false,
                    error = e.message
                )
            )
        }
    }

    suspend fun syncFromServer() {
        updateState(
            getCurrentState().copy(
                isLoading = true,
                error = null
            )
        )

        try {
            propertyRepository.syncProperties()
            loadProperties()
        } catch (e: Exception) {
            updateState(
                getCurrentState().copy(
                    isLoading = false,
                    error = e.message
                )
            )
        }
    }

    private fun generateId(): String {
        return "prop_${System.currentTimeMillis()}_${(1000..9999).random()}"
    }
}
