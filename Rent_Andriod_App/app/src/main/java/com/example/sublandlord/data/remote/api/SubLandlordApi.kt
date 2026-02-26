package com.example.sublandlord.data.remote.api

import com.example.sublandlord.data.remote.dto.PropertyDto
import com.example.sublandlord.data.remote.dto.LandlordDto
import com.example.sublandlord.data.remote.dto.TenantDto
import com.example.sublandlord.data.remote.dto.LandlordContractDto
import com.example.sublandlord.data.remote.dto.LeaseDto
import com.example.sublandlord.data.remote.dto.TransactionDto
import com.example.sublandlord.data.remote.dto.ReminderDto
import retrofit2.Response
import retrofit2.http.*

interface SubLandlordApi {

    // Properties
    @GET("properties")
    suspend fun getProperties(): Response<List<PropertyDto>>

    @GET("properties/{id}")
    suspend fun getPropertyById(@Path("id") id: String): Response<PropertyDto>

    @POST("properties")
    suspend fun createProperty(@Body property: PropertyDto): Response<PropertyDto>

    @PUT("properties/{id}")
    suspend fun updateProperty(@Path("id") id: String, @Body property: PropertyDto): Response<PropertyDto>

    @DELETE("properties/{id}")
    suspend fun deleteProperty(@Path("id") id: String): Response<Unit>

    // Landlords
    @GET("landlords")
    suspend fun getLandlords(): Response<List<LandlordDto>>

    @GET("landlords/{id}")
    suspend fun getLandlordById(@Path("id") id: String): Response<LandlordDto>

    @POST("landlords")
    suspend fun createLandlord(@Body landlord: LandlordDto): Response<LandlordDto>

    @PUT("landlords/{id}")
    suspend fun updateLandlord(@Path("id") id: String, @Body landlord: LandlordDto): Response<LandlordDto>

    @DELETE("landlords/{id}")
    suspend fun deleteLandlord(@Path("id") id: String): Response<Unit>

    // Tenants
    @GET("tenants")
    suspend fun getTenants(): Response<List<TenantDto>>

    @GET("tenants/{id}")
    suspend fun getTenantById(@Path("id") id: String): Response<TenantDto>

    @POST("tenants")
    suspend fun createTenant(@Body tenant: TenantDto): Response<TenantDto>

    @PUT("tenants/{id}")
    suspend fun updateTenant(@Path("id") id: String, @Body tenant: TenantDto): Response<TenantDto>

    @DELETE("tenants/{id}")
    suspend fun deleteTenant(@Path("id") id: String): Response<Unit>

    // Landlord Contracts
    @GET("contracts/landlord")
    suspend fun getLandlordContracts(): Response<List<LandlordContractDto>>

    @GET("contracts/landlord/{id}")
    suspend fun getLandlordContractById(@Path("id") id: String): Response<LandlordContractDto>

    @POST("contracts/landlord")
    suspend fun createLandlordContract(@Body contract: LandlordContractDto): Response<LandlordContractDto>

    @PUT("contracts/landlord/{id}")
    suspend fun updateLandlordContract(@Path("id") id: String, @Body contract: LandlordContractDto): Response<LandlordContractDto>

    @DELETE("contracts/landlord/{id}")
    suspend fun deleteLandlordContract(@Path("id") id: String): Response<Unit>

    // Leases
    @GET("leases")
    suspend fun getLeases(): Response<List<LeaseDto>>

    @GET("leases/{id}")
    suspend fun getLeaseById(@Path("id") id: String): Response<LeaseDto>

    @POST("leases")
    suspend fun createLease(@Body lease: LeaseDto): Response<LeaseDto>

    @PUT("leases/{id}")
    suspend fun updateLease(@Path("id") id: String, @Body lease: LeaseDto): Response<LeaseDto>

    @DELETE("leases/{id}")
    suspend fun deleteLease(@Path("id") id: String): Response<Unit>

    // Transactions
    @GET("transactions")
    suspend fun getTransactions(
        @Query("startDate") startDate: Long?,
        @Query("endDate") endDate: Long?
    ): Response<List<TransactionDto>>

    @GET("transactions/{id}")
    suspend fun getTransactionById(@Path("id") id: String): Response<TransactionDto>

    @POST("transactions")
    suspend fun createTransaction(@Body transaction: TransactionDto): Response<TransactionDto>

    @PUT("transactions/{id}")
    suspend fun updateTransaction(@Path("id") id: String, @Body transaction: TransactionDto): Response<TransactionDto>

    @DELETE("transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: String): Response<Unit>

    // Reminders
    @GET("reminders")
    suspend fun getReminders(): Response<List<ReminderDto>>

    @GET("reminders/{id}")
    suspend fun getReminderById(@Path("id") id: String): Response<ReminderDto>

    @POST("reminders")
    suspend fun createReminder(@Body reminder: ReminderDto): Response<ReminderDto>

    @PUT("reminders/{id}")
    suspend fun updateReminder(@Path("id") id: String, @Body reminder: ReminderDto): Response<ReminderDto>

    @DELETE("reminders/{id}")
    suspend fun deleteReminder(@Path("id") id: String): Response<Unit>

    @POST("reminders/{id}/send")
    suspend fun sendReminder(@Path("id") id: String): Response<Unit>

    // Authentication
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): Response<LoginResponse>
}

data class LoginRequest(
    val phone: String,
    val password: String
)

data class RegisterRequest(
    val phone: String,
    val password: String,
    val name: String
)

data class LoginResponse(
    val token: String,
    val refreshToken: String,
    val user: UserDto
)

data class RefreshTokenRequest(
    val refreshToken: String
)

data class UserDto(
    val id: String,
    val name: String,
    val phone: String,
    val email: String?
)
