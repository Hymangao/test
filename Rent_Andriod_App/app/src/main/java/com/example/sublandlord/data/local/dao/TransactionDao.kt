package com.example.sublandlord.data.local.dao

import androidx.room.*
import com.example.sublandlord.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    fun getTransactionById(transactionId: String): Flow<TransactionEntity?>

    @Query("SELECT * FROM transactions WHERE type = :type ORDER BY date DESC")
    fun getTransactionsByType(type: com.example.sublandlord.data.local.entity.TransactionType): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE category = :category ORDER BY date DESC")
    fun getTransactionsByCategory(category: com.example.sublandlord.data.local.entity.TransactionCategory): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE propertyId = :propertyId ORDER BY date DESC")
    fun getTransactionsByPropertyId(propertyId: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE roomId = :roomId ORDER BY date DESC")
    fun getTransactionsByRoomId(roomId: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE contractId = :contractId ORDER BY date DESC")
    fun getTransactionsByContractId(contractId: String): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE status = :status ORDER BY date DESC")
    fun getTransactionsByStatus(status: com.example.sublandlord.data.local.entity.TransactionStatus): Flow<List<TransactionEntity>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'INCOME' AND date >= :startTime AND date <= :endTime")
    suspend fun getTotalIncome(startTime: Long, endTime: Long): Float?

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'EXPENSE' AND date >= :startTime AND date <= :endTime")
    suspend fun getTotalExpense(startTime: Long, endTime: Long): Float?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<TransactionEntity>)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteTransactionById(transactionId: String)
}
