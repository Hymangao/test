package com.example.sublandlord.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sublandlord.presentation.contracts.AddContractScreen
import com.example.sublandlord.presentation.contracts.ContractsScreen
import com.example.sublandlord.presentation.home.HomeScreen
import com.example.sublandlord.presentation.profile.ProfileScreen
import com.example.sublandlord.presentation.properties.AddPropertyScreen
import com.example.sublandlord.presentation.properties.PropertiesScreen
import com.example.sublandlord.presentation.propertydetail.PropertyDetailScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route in bottomNavRoutes) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // Home screen
            composable(Screen.Home.route) {
                HomeScreen(
                    onAddPropertyClick = { navController.navigate(Screen.AddProperty.route) },
                    onAddContractClick = { navController.navigate(Screen.AddContract.createRoute()) },
                    onViewPropertiesClick = { navController.navigate(Screen.Properties.route) }
                )
            }

            // Properties screen
            composable(Screen.Properties.route) {
                PropertiesScreen(
                    onPropertyClick = { navController.navigate(Screen.PropertyDetail.createRoute(it)) },
                    onAddPropertyClick = { navController.navigate(Screen.AddProperty.route) }
                )
            }

            // Add property screen
            composable(Screen.AddProperty.route) {
                AddPropertyScreen(
                    onBack = { navController.popBackStack() },
                    onSaved = { navController.popBackStack() }
                )
            }

            // Property detail screen
            composable(
                route = Screen.PropertyDetail.route,
                arguments = listOf(navArgument("propertyId") { type = NavType.StringType })
            ) { backStackEntry ->
                val propertyId = backStackEntry.arguments?.getString("propertyId") ?: return@composable
                PropertyDetailScreen(
                    propertyId = propertyId,
                    onBack = { navController.popBackStack() },
                    onEditProperty = { navController.navigate(Screen.EditProperty.createRoute(propertyId)) },
                    onAddContract = { navController.navigate(Screen.AddContract.createRoute(propertyId)) }
                )
            }

            // Contracts screen
            composable(Screen.Contracts.route) {
                ContractsScreen(
                    onBack = { navController.popBackStack() },
                    onAddContract = { navController.navigate(Screen.AddContract.createRoute()) }
                )
            }

            // Add contract screen
            composable(
                route = Screen.AddContract.route,
                arguments = listOf(navArgument("propertyId") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                val propertyId = backStackEntry.arguments?.getString("propertyId") ?: ""
                AddContractScreen(
                    propertyIdPrefill = propertyId.takeIf { it.isNotBlank() },
                    onBack = { navController.popBackStack() },
                    onSubmitted = {
                        navController.popBackStack()
                    }
                )
            }

            // Profile screen
            composable(Screen.Profile.route) {
                ProfileScreen(onNavigate = {
                    navController.navigate(Screen.ProfileSection.createRoute(it))
                })
            }

            composable(
                route = Screen.ProfileSection.route,
                arguments = listOf(navArgument("section") { type = NavType.StringType })
            ) {
                val section = it.arguments?.getString("section") ?: ""
                // TODO: Hook up real screens for profile sections
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("即将推出") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        Text(
                            text = "功能建设中",
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

private val bottomNavRoutes = setOf(
    Screen.Home.route,
    Screen.Properties.route,
    Screen.Contracts.route,
    Screen.Profile.route
)

private data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

private val bottomNavItems = listOf(
    BottomNavItem(Screen.Home.route, "首页", Icons.Default.Home),
    BottomNavItem(Screen.Properties.route, "房屋", Icons.Default.Business),
    BottomNavItem(Screen.Contracts.route, "合同", Icons.Default.Description),
    BottomNavItem(Screen.Profile.route, "我的", Icons.Default.Person)
)

// Placeholder screens
@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {
    Text("Home Screen")
}

@Composable
fun AddPropertyScreen(onBack: () -> Unit, onSave: () -> Unit) {
    Text("Add Property Screen")
}

@Composable
fun ContractsScreen(navController: androidx.navigation.NavController) {
    Text("Contracts Screen")
}

@Composable
fun ProfileScreen(navController: androidx.navigation.NavController) {
    Text("Profile Screen")
}
