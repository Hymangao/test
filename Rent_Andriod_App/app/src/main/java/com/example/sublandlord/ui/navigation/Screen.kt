package com.example.sublandlord.ui.navigation

sealed class Screen(val route: String) {
    // Bottom navigation tabs
    object Home : Screen("home")
    object Properties : Screen("properties")
    object Contracts : Screen("contracts")
    object Profile : Screen("profile")

    // Detail screens
    object PropertyDetail : Screen("property_detail/{propertyId}") {
        fun createRoute(propertyId: String) = "property_detail/$propertyId"
    }

    object AddProperty : Screen("add_property")
    object AddContract : Screen("add_contract?propertyId={propertyId}") {
        fun createRoute(propertyId: String? = null): String {
            val id = propertyId?.takeIf { it.isNotBlank() } ?: ""
            return "add_contract?propertyId=$id"
        }
    }
    object EditProperty : Screen("edit_property/{propertyId}") {
        fun createRoute(propertyId: String) = "edit_property/$propertyId"
    }

    object LandlordDetail : Screen("landlord_detail/{landlordId}") {
        fun createRoute(landlordId: String) = "landlord_detail/$landlordId"
    }

    object TenantDetail : Screen("tenant_detail/{tenantId}") {
        fun createRoute(tenantId: String) = "tenant_detail/$tenantId"
    }

    object ContractDetail : Screen("contract_detail/{contractId}") {
        fun createRoute(contractId: String) = "contract_detail/$contractId"
    }

    object ProfileSection : Screen("profile_section/{section}") {
        fun createRoute(section: String) = "profile_section/$section"
    }

    object Dashboard : Screen("dashboard")
    object Reminders : Screen("reminders")
}
