@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.sublandlord.presentation.contracts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContractsScreen(
    onBack: () -> Unit,
    onAddContract: () -> Unit,
    viewModel: ContractsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("合同管理") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = onAddContract) {
                        Icon(Icons.Filled.Add, contentDescription = "新增合同")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ContractsTabRow(
                selectedTab = uiState.selectedTab,
                onTabSelected = viewModel::selectTab
            )

            when (uiState.selectedTab) {
                ContractsTab.LANDLORD -> {
                    ContractList(
                        summaries = uiState.landlordContracts,
                        emptyText = "暂无房东合同"
                    )
                }
                ContractsTab.LEASE -> {
                    ContractList(
                        summaries = uiState.leases,
                        emptyText = "暂无租约"
                    )
                }
            }
        }
    }
}

@Composable
private fun ContractsTabRow(
    selectedTab: ContractsTab,
    onTabSelected: (ContractsTab) -> Unit
) {
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        ContractsTab.values().forEachIndexed { index, tab ->
            val selected = tab == selectedTab
            val shape = SegmentedButtonDefaults.itemShape(index, ContractsTab.values().size)
            SegmentedButton(
                selected = selected,
                onClick = { onTabSelected(tab) },
                shape = shape
            ) {
                Text(
                    text = when (tab) {
                        ContractsTab.LANDLORD -> "房东合同"
                        ContractsTab.LEASE -> "租约"
                    },
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
private fun ContractList(
    summaries: List<ContractSummary>,
    emptyText: String
) {
    if (summaries.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emptyText, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "点击 + 新增" , style = MaterialTheme.typography.bodySmall)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(summaries) { summary ->
                ContractCard(summary)
            }
        }
    }
}

@Composable
private fun ContractCard(summary: ContractSummary) {
    androidx.compose.material3.Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = summary.typeLabel, style = MaterialTheme.typography.labelMedium)
                    Text(
                        text = summary.propertyLabel,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                StatusChip(status = summary.status)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "对方：${summary.counterpartLabel}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "租金：¥${summary.monthlyRent}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "期限：${formatDate(summary.startDate)} - ${formatDate(summary.endDate)}")
        }
    }
}

@Composable
private fun StatusChip(status: com.example.sublandlord.data.local.entity.ContractStatus) {
    val label = when (status) {
        com.example.sublandlord.data.local.entity.ContractStatus.DRAFT -> "草稿"
        com.example.sublandlord.data.local.entity.ContractStatus.ACTIVE -> "进行中"
        com.example.sublandlord.data.local.entity.ContractStatus.EXPIRED -> "已到期"
        com.example.sublandlord.data.local.entity.ContractStatus.TERMINATED -> "已终止"
    }

    androidx.compose.material3.AssistChip(onClick = { }, label = { Text(label) })
}

private fun formatDate(timestamp: Long): String {
    val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
    return formatter.format(java.util.Date(timestamp))
}
