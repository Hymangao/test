package com.example.sublandlord.data.repository

import com.example.sublandlord.data.local.dao.LandlordContractDao
import com.example.sublandlord.data.local.dao.LeaseDao
import com.example.sublandlord.data.local.entity.LandlordContractEntity
import com.example.sublandlord.data.local.entity.LeaseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContractRepository @Inject constructor(
    private val landlordContractDao: LandlordContractDao,
    private val leaseDao: LeaseDao
) {
    fun getLandlordContracts(): Flow<List<LandlordContractEntity>> =
        landlordContractDao.getAllContracts()

    fun getLeases(): Flow<List<LeaseEntity>> =
        leaseDao.getAllLeases()

    suspend fun createLandlordContract(contract: LandlordContractEntity) {
        landlordContractDao.insertContract(contract)
    }

    suspend fun createLease(lease: LeaseEntity) {
        leaseDao.insertLease(lease)
    }
}
