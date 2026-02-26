package com.example.sublandlord.presentation.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.runtime.collectAsState
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
import com.example.sublandlord.data.local.entity.PropertyType
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPropertyScreen(
    onBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: PropertiesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    var name by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var district by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf("") }
    var floor by rememberSaveable { mutableStateOf("") }
    var rooms by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var propertyType by rememberSaveable { mutableStateOf(PropertyType.ENTIRE_RENT) }

    var showTypeMenu by remember { mutableStateOf(false) }

    val areaError = area.isNotBlank() && area.toFloatOrNull()?.let { it <= 0f } != false
    val roomsError = rooms.isNotBlank() && rooms.toIntOrNull()?.let { it < 1 } != false

    val mandatoryFilled = name.isNotBlank() && city.isNotBlank() && district.isNotBlank() &&
        address.isNotBlank() && area.toFloatOrNull()?.let { it > 0f } == true &&
        rooms.toIntOrNull()?.let { it >= 1 } == true

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("添加房屋") },
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
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (uiState.error != null) {
                Text(
                    text = uiState.error ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("房屋名称") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("城市") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = district,
                        onValueChange = { district = it },
                        label = { Text("区域") },
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("详细地址") },
                    modifier = Modifier.fillMaxWidth()
                )

                ExposedDropdownMenuBox(
                    expanded = showTypeMenu,
                    onExpandedChange = { showTypeMenu = !showTypeMenu }
                ) {
                    OutlinedTextField(
                        value = mapPropertyType(propertyType),
                        onValueChange = {},
                        label = { Text("房屋类型") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showTypeMenu) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = showTypeMenu,
                        onDismissRequest = { showTypeMenu = false }
                    ) {
                        PropertyType.values().forEach { type ->
                            DropdownMenuItem(
                                text = { Text(mapPropertyType(type)) },
                                onClick = {
                                    propertyType = type
                                    showTypeMenu = false
                                }
                            )
                        }
                    }
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = area,
                        onValueChange = { area = it },
                        label = { Text("面积 (㎡)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        supportingText = {
                            if (areaError) Text("请输入大于0的数值", color = MaterialTheme.colorScheme.error)
                        },
                        isError = areaError,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = rooms,
                        onValueChange = { rooms = it },
                        label = { Text("房间数") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        supportingText = {
                            if (roomsError) Text("至少1间", color = MaterialTheme.colorScheme.error)
                        },
                        isError = roomsError,
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = floor,
                    onValueChange = { floor = it },
                    label = { Text("楼层") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("备注") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 120.dp),
                    maxLines = 5
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("取消")
                }

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        scope.launch {
                            viewModel.createProperty(
                                name = name,
                                address = address,
                                city = city,
                                district = district,
                                type = propertyType,
                                area = area.toFloatOrNull() ?: 0f,
                                floor = floor,
                                totalRooms = rooms.toIntOrNull() ?: 0
                            )
                            onSaved()
                        }
                    },
                    modifier = Modifier.weight(1f),
                    enabled = mandatoryFilled && !uiState.isLoading
                ) {
                    Text("保存")
                }
            }
        }
    }
}

private fun mapPropertyType(type: PropertyType): String = when (type) {
    PropertyType.ENTIRE_RENT -> "整租"
    PropertyType.SHARED_RENT -> "合租"
}
