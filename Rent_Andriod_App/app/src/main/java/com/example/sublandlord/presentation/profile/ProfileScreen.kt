package com.example.sublandlord.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onNavigate: (String) -> Unit = {}
) {
    val uiState = remember {
        ProfileUiState(
            userName = "王小二",
            subtitle = "二房东助手",
            totalProperties = 12,
            activeContracts = 18,
            monthlyIncome = "¥45,800"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("我的") })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { ProfileHeader(uiState) }
            item { StatsRow(uiState) }
            item { MenuSection(onNavigate = onNavigate) }
            item { ActionButtons() }
        }
    }
}

@Composable
private fun ProfileHeader(uiState: ProfileUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.userName.take(1),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column {
                Text(
                    text = uiState.userName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = uiState.subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Composable
private fun StatsRow(uiState: ProfileUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(title = "房源数", value = uiState.totalProperties.toString(), modifier = Modifier.weight(1f))
        StatCard(title = "进行中合同", value = uiState.activeContracts.toString(), modifier = Modifier.weight(1f))
        StatCard(title = "本月收入", value = uiState.monthlyIncome, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun MenuSection(onNavigate: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            MenuItem(
                title = "账号设置",
                subtitle = "个人信息与登录安全",
                icon = Icons.Filled.Settings,
                onClick = { onNavigate("account") }
            )
            Divider()
            MenuItem(
                title = "数据备份",
                subtitle = "导出本地数据到云端",
                icon = Icons.Filled.Backup,
                onClick = { onNavigate("backup") }
            )
            Divider()
            MenuItem(
                title = "通知设置",
                subtitle = "收租提醒 / 合同提醒",
                icon = Icons.Filled.Notifications,
                onClick = { onNavigate("notifications") }
            )
            Divider()
            MenuItem(
                title = "关于应用",
                subtitle = "版本信息 / 使用指南",
                icon = Icons.Filled.Info,
                onClick = { onNavigate("about") }
            )
        }
    }
}

@Composable
private fun MenuItem(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(text = title, fontWeight = FontWeight.Medium) },
        supportingContent = { Text(text = subtitle, maxLines = 1, overflow = TextOverflow.Ellipsis) },
        leadingContent = { Icon(icon, contentDescription = null) },
        trailingContent = { Icon(Icons.Filled.ArrowForwardIos, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .then(Modifier.background(Color.Transparent))
            .clickable(onClick = onClick),
        tonalElevation = 0.dp
    )
}

@Composable
private fun ActionButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* 导出数据 */ }
        ) {
            Text("导出数据")
        }

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* 联系客服 */ }
        ) {
            Text("联系客服")
        }
    }
}

data class ProfileUiState(
    val userName: String,
    val subtitle: String,
    val totalProperties: Int,
    val activeContracts: Int,
    val monthlyIncome: String
)
