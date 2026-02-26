package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.ReminderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders ORDER BY targetDate ASC")
    fun getAllReminders(): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE id = :reminderId")
    fun getReminderById(reminderId: String): Flow<ReminderEntity?>

    @Query("SELECT * FROM reminders WHERE type = :type ORDER BY targetDate ASC")
    fun getRemindersByType(type: com.example.sublandlord.data.local.entity.ReminderType): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE status = :status ORDER BY targetDate ASC")
    fun getRemindersByStatus(status: com.example.sublandlord.data.local.entity.ReminderStatus): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE priority = :priority ORDER BY targetDate ASC")
    fun getRemindersByPriority(priority: com.example.sublandlord.data.local.entity.ReminderPriority): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE targetDate <= :timestamp AND status = 'PENDING'")
    fun getDueReminders(timestamp: Long): Flow<List<ReminderEntity>>

    @Query("SELECT * FROM reminders WHERE relatedEntityId = :entityId AND relatedEntityType = :entityType")
    fun getRemindersByEntity(
        entityId: String,
        entityType: com.example.sublandlord.data.local.entity.RelatedEntityType
    ): Flow<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminders(reminders: List<ReminderEntity>)

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Delete
    suspend fun deleteReminder(reminder: ReminderEntity)

    @Query("DELETE FROM reminders WHERE id = :reminderId")
    suspend fun deleteReminderById(reminderId: String)

    @Query("DELETE FROM reminders WHERE status = 'COMPLETED'")
    suspend fun deleteCompletedReminders()
}
