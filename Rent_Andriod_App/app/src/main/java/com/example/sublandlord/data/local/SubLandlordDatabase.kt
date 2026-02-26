package com.example.sublandlord.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sublandlord.data.local.converter.Converters
import com.example.sublandlord.data.local.dao.*
import com.example.sublandlord.data.local.entity.*

@Database(
    entities = [
        PropertyEntity::class,
        RoomEntity::class,
        LandlordEntity::class,
        TenantEntity::class,
        LandlordContractEntity::class,
        LeaseEntity::class,
        TransactionEntity::class,
        ReminderEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SubLandlordDatabase : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao
    abstract fun roomDao(): RoomDao
    abstract fun landlordDao(): LandlordDao
    abstract fun tenantDao(): TenantDao
    abstract fun landlordContractDao(): LandlordContractDao
    abstract fun leaseDao(): LeaseDao
    abstract fun transactionDao(): TransactionDao
    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: SubLandlordDatabase? = null

        fun getDatabase(context: Context): SubLandlordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubLandlordDatabase::class.java,
                    "sublandlord_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
