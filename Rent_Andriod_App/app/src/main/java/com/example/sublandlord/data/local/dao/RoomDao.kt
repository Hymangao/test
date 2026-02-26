package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT * FROM rooms WHERE propertyId = :propertyId ORDER BY createdAt DESC")
    fun getRoomsByPropertyId(propertyId: String): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE id = :roomId")
    fun getRoomById(roomId: String): Flow<RoomEntity?>

    @Query("SELECT * FROM rooms WHERE status = :status")
    fun getRoomsByStatus(status: com.example.sublandlord.data.local.entity.RoomStatus): Flow<List<RoomEntity>>

    @Query("SELECT * FROM rooms WHERE propertyId = :propertyId AND status = :status")
    fun getRoomsByPropertyAndStatus(
        propertyId: String,
        status: com.example.sublandlord.data.local.entity.RoomStatus
    ): Flow<List<RoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoom(room: RoomEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<RoomEntity>)

    @Update
    suspend fun updateRoom(room: RoomEntity)

    @Delete
    suspend fun deleteRoom(room: RoomEntity)

    @Query("DELETE FROM rooms WHERE id = :roomId")
    suspend fun deleteRoomById(roomId: String)

    @Query("DELETE FROM rooms WHERE propertyId = :propertyId")
    suspend fun deleteRoomsByPropertyId(propertyId: String)
}
