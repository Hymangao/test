package com.example.sublandlord.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.sublandlord.data.local.entity.*

class Converters {
    private val gson = Gson()

    // Enums
    @TypeConverter
    fun fromPropertyType(value: PropertyType): String = value.name

    @TypeConverter
    fun toPropertyType(value: String): PropertyType = enumValueOf(value)

    @TypeConverter
    fun fromPropertyStatus(value: PropertyStatus): String = value.name

    @TypeConverter
    fun toPropertyStatus(value: String): PropertyStatus = enumValueOf(value)

    @TypeConverter
    fun fromRoomType(value: RoomType): String = value.name

    @TypeConverter
    fun toRoomType(value: String): RoomType = enumValueOf(value)

    @TypeConverter
    fun fromRoomStatus(value: RoomStatus): String = value.name

    @TypeConverter
    fun toRoomStatus(value: String): RoomStatus = enumValueOf(value)

    @TypeConverter
    fun fromTenantStatus(value: TenantStatus): String = value.name

    @TypeConverter
    fun toTenantStatus(value: String): TenantStatus = enumValueOf(value)

    @TypeConverter
    fun fromDocumentType(value: DocumentType): String = value.name

    @TypeConverter
    fun toDocumentType(value: String): DocumentType = enumValueOf(value)

    @TypeConverter
    fun fromPaymentFrequency(value: PaymentFrequency): String = value.name

    @TypeConverter
    fun toPaymentFrequency(value: String): PaymentFrequency = enumValueOf(value)

    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String = value.name

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod = enumValueOf(value)

    @TypeConverter
    fun fromContractStatus(value: ContractStatus): String = value.name

    @TypeConverter
    fun toContractStatus(value: String): ContractStatus = enumValueOf(value)

    @TypeConverter
    fun fromTransactionType(value: TransactionType): String = value.name

    @TypeConverter
    fun toTransactionType(value: String): TransactionType = enumValueOf(value)

    @TypeConverter
    fun fromTransactionCategory(value: TransactionCategory): String = value.name

    @TypeConverter
    fun toTransactionCategory(value: String): TransactionCategory = enumValueOf(value)

    @TypeConverter
    fun fromTransactionStatus(value: TransactionStatus): String = value.name

    @TypeConverter
    fun toTransactionStatus(value: String): TransactionStatus = enumValueOf(value)

    @TypeConverter
    fun fromReminderType(value: ReminderType): String = value.name

    @TypeConverter
    fun toReminderType(value: String): ReminderType = enumValueOf(value)

    @TypeConverter
    fun fromRelatedEntityType(value: RelatedEntityType): String = value.name

    @TypeConverter
    fun toRelatedEntityType(value: String): RelatedEntityType = enumValueOf(value)

    @TypeConverter
    fun fromReminderStatus(value: ReminderStatus): String = value.name

    @TypeConverter
    fun toReminderStatus(value: String): ReminderStatus = enumValueOf(value)

    @TypeConverter
    fun fromReminderPriority(value: ReminderPriority): String = value.name

    @TypeConverter
    fun toReminderPriority(value: String): ReminderPriority = enumValueOf(value)

    // Lists and Objects
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromDocumentList(list: List<Document>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toDocumentList(json: String): List<Document> {
        val type = object : TypeToken<List<Document>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromRenewalRecordList(list: List<RenewalRecord>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toRenewalRecordList(json: String): List<RenewalRecord> {
        val type = object : TypeToken<List<RenewalRecord>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromLateFeePolicy(policy: LateFeePolicy?): String {
        return gson.toJson(policy)
    }

    @TypeConverter
    fun toLateFeePolicy(json: String): LateFeePolicy? {
        return if (json.isEmpty()) null else gson.fromJson(json, LateFeePolicy::class.java)
    }

    @TypeConverter
    fun fromUtilitiesPolicy(policy: UtilitiesPolicy): String {
        return gson.toJson(policy)
    }

    @TypeConverter
    fun toUtilitiesPolicy(json: String): UtilitiesPolicy {
        return gson.fromJson(json, UtilitiesPolicy::class.java)
    }

    @TypeConverter
    fun fromBankInfo(bankInfo: BankInfo?): String {
        return gson.toJson(bankInfo)
    }

    @TypeConverter
    fun toBankInfo(json: String): BankInfo? {
        return if (json.isEmpty()) null else gson.fromJson(json, BankInfo::class.java)
    }

    @TypeConverter
    fun fromContact(contact: Contact?): String {
        return gson.toJson(contact)
    }

    @TypeConverter
    fun toContact(json: String): Contact? {
        return if (json.isEmpty()) null else gson.fromJson(json, Contact::class.java)
    }

    @TypeConverter
    fun fromLongList(list: List<Long>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toLongList(json: String): List<Long> {
        val type = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(json, type)
    }
}
