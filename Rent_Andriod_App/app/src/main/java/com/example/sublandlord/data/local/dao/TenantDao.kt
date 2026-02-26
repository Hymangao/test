package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.TenantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TenantDao {
    @Query("SELECT * FROM tenants ORDER BY updatedAt DESC")
    fun getAllTenants(): Flow<List<TenantEntity>>

    @Query("SELECT * FROM tenants WHERE id = :tenantId")
    fun getTenantById(tenantId: String): Flow<TenantEntity?>

    @Query("SELECT * FROM tenants WHERE roomId = :roomId")
    fun getTenantsByRoomId(roomId: String): Flow<List<TenantEntity>>

    @Query("SELECT * FROM tenants WHERE status = :status")
    fun getTenantsByStatus(status: com.example.sublandlord.data.local.entity.TenantStatus): Flow<List<TenantEntity>>

    @Query("SELECT * FROM tenants WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'")
    fun searchTenants(query: String): Flow<List<TenantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTenant(tenant: TenantEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTenants(tenants: List<TenantEntity>)

    @Update
    suspend fun updateTenant(tenant: TenantEntity)

    @Delete
    suspend fun deleteTenant(tenant: TenantEntity)

    @Query("DELETE FROM tenants WHERE id = :tenantId")
    suspend fun deleteTenantById(tenantId: String)

    @Query("UPDATE tenants SET roomId = NULL WHERE roomId = :roomId")
    suspend fun vacateRoom(roomId: String)
}
