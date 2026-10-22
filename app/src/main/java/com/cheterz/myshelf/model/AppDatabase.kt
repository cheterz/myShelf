package com.cheterz.myshelf.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheterz.myshelf.model.items.ItemDAO
import com.cheterz.myshelf.model.items.ItemEntity
import com.cheterz.myshelf.model.loans.LoanDAO
import com.cheterz.myshelf.model.loans.LoanEntity

@Database(entities = [ItemEntity::class, LoanEntity::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDAO(): ItemDAO
    abstract fun loanDAO(): LoanDAO
}