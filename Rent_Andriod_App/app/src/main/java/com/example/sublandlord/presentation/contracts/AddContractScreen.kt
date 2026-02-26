package com.example.sublandlord.presentation.contracts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContractScreen(
    propertyIdPrefill: String? = null,
    onBack: () -> Unit,
    onSubmitted: (ContractFormInput) -> Unit,
    viewModel: ContractsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    var selectedTab by rememberSaveable { mutableStateOf(ContractsTab.LANDLORD) }
    var propertyId by rememberSaveable { mutableStateOf(propertyIdPrefill.orEmpty()) }
    var counterpartId by rememberSaveable { mutableStateOf("") }
    var startDate by rememberSaveable { mutableStateOf("") }
    var endDate by rememberSaveable { mutableStateOf("") }
    var monthlyRent by rememberSaveable { mutableStateOf("") }

    var showTypeMenu by remember { mutableStateOf(false) }

    val rentError = monthlyRent.isNotBlank() && monthlyRent.toFloatOrNull()?.let { it <= 0f } != false
    val canSubmit = propertyId.isNotBlank() && counterpartId.isNotBlank() &&
        startDate.length == 10 && endDate.length == 10 &&
        monthlyRent.toFloatOrNull()?.let { it > 0f } == true

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("新增合同") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = showTypeMenu,
                    onExpandedChange = { showTypeMenu = !showTypeMenu }
                ) {
                    OutlinedTextField(
                        value = when (selectedTab) {
                            ContractsTab.LANDLORD -> "房东合同"
                            ContractsTab.LEASE -> "租约"
                        },
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("合同类型") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTypeMenu) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = showTypeMenu,
                        onDismissRequest = { showTypeMenu = false }
                    ) {
                        ContractsTab.values().forEach { tab ->
                            DropdownMenuItem(
                                text = { Text(if (tab == ContractsTab.LANDLORD) "房东合同" else "租约") },
                                onClick = {
                                    selectedTab = tab
                                    showTypeMenu = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = propertyId,
                    onValueChange = { propertyId = it },
                    label = { Text("房源/房间 ID") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = counterpartId,
                    onValueChange = { counterpartId = it },
                    label = {
                        Text(
                            if (selectedTab == ContractsTab.LANDLORD) "房东 ID" else "租客 ID"
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        label = { Text("起始日期 (yyyy-MM-dd)") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = endDate,
                        onValueChange = { endDate = it },
                        label = { Text("结束日期") },
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = monthlyRent,
                    onValueChange = { monthlyRent = it },
                    label = { Text("月租金") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = rentError,
                    supportingText = {
                        if (rentError) Text("请输入有效租金", color = MaterialTheme.colorScheme.error)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(modifier = Modifier.weight(1f), onClick = onBack) {
                    Text("取消")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    enabled = canSubmit,
                    onClick = {
                        focusManager.clearFocus()
                        scope.launch {
                            onSubmitted(
                                ContractFormInput(
                                    contractType = selectedTab,
                                    propertyId = propertyId,
                                    counterpartId = counterpartId,
                                    startDate = parseDate(startDate),
                                    endDate = parseDate(endDate),
                                    monthlyRent = monthlyRent.toFloatOrNull() ?: 0f
                                )
                            )
                        }
                    }
                ) {
                    Text("保存草稿")
                }
            }
        }
    }
}

private fun parseDate(input: String): Long {
    return try {
        val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        formatter.parse(input)?.time ?: System.currentTimeMillis()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
}
