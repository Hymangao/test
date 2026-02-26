package com.example.sublandlord.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landlords")
data class LandlordEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    val idCard: String?, // Encrypted
    val wechat: String?,
    val bankInfo: String?, // JSON of BankInfo
    val notes: String?,
    val createdAt: Long,
    val updatedAt: Long
)

data class BankInfo(
    val bankName: String,
    val accountNumber: String,
    val accountName: String,
    val branchName: String?
)
