package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.LeaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaseDao {
    @Query("SELECT * FROM leases ORDER BY createdAt DESC")
    fun getAllLeases(): Flow<List<LeaseEntity>>

    @Query("SELECT * FROM leases WHERE id = :leaseId")
    fun getLeaseById(leaseId: String): Flow<LeaseEntity?>

    @Query("SELECT * FROM leases WHERE roomId = :roomId")
    fun getLeasesByRoomId(roomId: String): Flow<List<LeaseEntity>>

    @Query("SELECT * FROM leases WHERE tenantId = :tenantId")
    fun getLeasesByTenantId(tenantId: String): Flow<List<LeaseEntity>>

    @Query("SELECT * FROM leases WHERE status = :status")
    fun getLeasesByStatus(status: com.example.sublandlord.data.local.entity.ContractStatus): Flow<List<LeaseEntity>>

    @Query("SELECT * FROM leases WHERE endDate <= :timestamp AND status = 'ACTIVE'")
    fun getExpiringLeases(timestamp: Long): Flow<List<LeaseEntity>>

    @Query("SELECT * FROM leases WHERE roomId = :roomId AND status = 'ACTIVE'")
    fun getActiveLeaseByRoomId(roomId: String): Flow<LeaseEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLease(lease: LeaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeases(leases: List<LeaseEntity>)

    @Update
    suspend fun updateLease(lease: LeaseEntity)

    @Delete
    suspend fun deleteLease(lease: LeaseEntity)

    @Query("DELETE FROM leases WHERE id = :leaseId")
    suspend fun deleteLeaseById(leaseId: String)

    @Query("UPDATE leases SET status = 'EXPIRED' WHERE endDate < :timestamp AND status = 'ACTIVE'")
    suspend fun updateExpiredLeases(timestamp: Long)
}
