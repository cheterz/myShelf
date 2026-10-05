package com.cheterz.myshelf.model.loans

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDAO {
    @Transaction
    @Query("SELECT * FROM loans")
    fun getAllLoansWithItems(): Flow<List<LoanWithItem>>

    @Insert
    suspend fun insertLoan(loan: LoanEntity)

    @Delete
    suspend fun deleteLoan(loan: LoanEntity)

    @Update
    suspend fun updateLoan(loan: LoanEntity)
}