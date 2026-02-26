package com.example.sublandlord.di

import android.content.Context
import com.example.sublandlord.data.local.SubLandlordDatabase
import com.example.sublandlord.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SubLandlordDatabase {
        return SubLandlordDatabase.getDatabase(context)
    }

    @Provides
    fun providePropertyDao(database: SubLandlordDatabase): PropertyDao {
        return database.propertyDao()
    }

    @Provides
    fun provideRoomDao(database: SubLandlordDatabase): RoomDao {
        return database.roomDao()
    }

    @Provides
    fun provideLandlordDao(database: SubLandlordDatabase): LandlordDao {
        return database.landlordDao()
    }

    @Provides
    fun provideTenantDao(database: SubLandlordDatabase): TenantDao {
        return database.tenantDao()
    }

    @Provides
    fun provideLandlordContractDao(database: SubLandlordDatabase): LandlordContractDao {
        return database.landlordContractDao()
    }

    @Provides
    fun provideLeaseDao(database: SubLandlordDatabase): LeaseDao {
        return database.leaseDao()
    }

    @Provides
    fun provideTransactionDao(database: SubLandlordDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    fun provideReminderDao(database: SubLandlordDatabase): ReminderDao {
        return database.reminderDao()
    }
}
