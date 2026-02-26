package com.example.sublandlord.presentation.propertydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sublandlord.data.local.entity.PropertyEntity
import com.example.sublandlord.data.local.entity.PropertyStatus
import com.example.sublandlord.data.local.entity.PropertyType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyDetailScreen(
    propertyId: String,
    onBack: () -> Unit,
    onEditProperty: (String) -> Unit,
    onAddContract: (String) -> Unit,
    viewModel: PropertyDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(propertyId) {
        viewModel.loadProperty(propertyId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.property?.name ?: "房屋详情",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = { onEditProperty(propertyId) }) {
                        Icon(Icons.Default.Edit, contentDescription = "编辑")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { uiState.property?.let { onAddContract(it.id) } }) {
                Icon(Icons.Default.NoteAdd, contentDescription = "添加合同")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Text(
                        text = uiState.error ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.property != null -> {
                    PropertyDetailContent(
                        uiState = uiState,
                        onEditProperty = { onEditProperty(propertyId) }
                    )
                }
            }
        }
    }
}

@Composable
private fun PropertyDetailContent(
    uiState: PropertyDetailUiState,
    onEditProperty: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PropertySummaryCard(uiState.property!!, uiState.occupancyOverview)
        }

        item {
            QuickStatsRow()
        }

        item {
            MaintenanceOverviewCard(uiState.maintenanceOverview)
        }

        item {
            ActionSection(onEditProperty = onEditProperty)
        }
    }
}

@Composable
private fun PropertySummaryCard(property: PropertyEntity, occupancy: OccupancyOverview) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = property.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = property.address,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoChip(title = "类型", value = mapPropertyType(property.type))
                InfoChip(title = "面积", value = "${property.area}㎡")
                InfoChip(title = "房间", value = "${property.totalRooms}间")
            }
            Spacer(modifier = Modifier.height(12.dp))
            StatusChip(status = property.status)
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "入住率 ${(occupancy.occupancyRate * 100).toInt()}%",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = occupancy.occupancyRate.coerceIn(0f, 1f),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${occupancy.occupiedRooms}/${occupancy.totalRooms} 间已出租",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StatusChip(status: PropertyStatus) {
    val (label, color) = when (status) {
        PropertyStatus.VACANT -> "空置" to MaterialTheme.colorScheme.primary
        PropertyStatus.RENTED -> "在租" to MaterialTheme.colorScheme.tertiary
        PropertyStatus.DECORATING -> "装修中" to MaterialTheme.colorScheme.secondary
        PropertyStatus.MAINTENANCE -> "维护中" to MaterialTheme.colorScheme.error
    }

    AssistChip(
        onClick = {},
        label = { Text(label) },
        colors = AssistChipDefaults.assistChipColors(
            labelColor = color
        )
    )
}

@Composable
private fun QuickStatsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        QuickStatCard(title = "月收入", value = "¥12.3k", modifier = Modifier.weight(1f))
        QuickStatCard(title = "空置天数", value = "12天", modifier = Modifier.weight(1f))
        QuickStatCard(title = "ROI", value = "8.5%", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun QuickStatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun InfoChip(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            tonalElevation = 2.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = value,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun MaintenanceOverviewCard(overview: MaintenanceOverview) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "维护概览",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "待处理工单：${overview.pendingTickets}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "最近维护：${overview.lastMaintenanceDate?.let { formatDaysAgo(it) } ?: "暂无"}")
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "即将安排：${overview.upcomingSchedule ?: "暂无"}")
        }
    }
}

@Composable
private fun ActionSection(onEditProperty: () -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "操作",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            AssistChip(onClick = onEditProperty, label = { Text("编辑房屋信息") })
            Spacer(modifier = Modifier.height(8.dp))
            AssistChip(onClick = { /* TODO */ }, label = { Text("上传照片") })
            Spacer(modifier = Modifier.height(8.dp))
            AssistChip(onClick = { /* TODO */ }, label = { Text("创建巡检任务") })
        }
    }
}

private fun mapPropertyType(type: PropertyType): String = when (type) {
    PropertyType.ENTIRE_RENT -> "整租"
    PropertyType.SHARED_RENT -> "合租"
}

private fun formatDaysAgo(timestamp: Long): String {
    val days = ((System.currentTimeMillis() - timestamp) / 86_400_000L).coerceAtLeast(0)
    return if (days == 0L) "今天" else "${days}天前"
}
