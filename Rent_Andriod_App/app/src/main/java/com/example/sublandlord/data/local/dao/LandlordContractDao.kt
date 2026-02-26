package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.LandlordContractEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LandlordContractDao {
    @Query("SELECT * FROM landlord_contracts ORDER BY createdAt DESC")
    fun getAllContracts(): Flow<List<LandlordContractEntity>>

    @Query("SELECT * FROM landlord_contracts WHERE id = :contractId")
    fun getContractById(contractId: String): Flow<LandlordContractEntity?>

    @Query("SELECT * FROM landlord_contracts WHERE propertyId = :propertyId")
    fun getContractsByPropertyId(propertyId: String): Flow<List<LandlordContractEntity>>

    @Query("SELECT * FROM landlord_contracts WHERE landlordId = :landlordId")
    fun getContractsByLandlordId(landlordId: String): Flow<List<LandlordContractEntity>>

    @Query("SELECT * FROM landlord_contracts WHERE status = :status")
    fun getContractsByStatus(status: com.example.sublandlord.data.local.entity.ContractStatus): Flow<List<LandlordContractEntity>>

    @Query("SELECT * FROM landlord_contracts WHERE endDate <= :timestamp AND status = 'ACTIVE'")
    fun getExpiringContracts(timestamp: Long): Flow<List<LandlordContractEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContract(contract: LandlordContractEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContracts(contracts: List<LandlordContractEntity>)

    @Update
    suspend fun updateContract(contract: LandlordContractEntity)

    @Delete
    suspend fun deleteContract(contract: LandlordContractEntity)

    @Query("DELETE FROM landlord_contracts WHERE id = :contractId")
    suspend fun deleteContractById(contractId: String)
}
