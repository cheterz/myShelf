package com.cheterz.myshelf.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cheterz.myshelf.model.items.ItemDAO
import com.cheterz.myshelf.model.items.ItemEntity
import com.cheterz.myshelf.model.loans.LoanDAO
import com.cheterz.myshelf.model.loans.LoanEntity

@Database(entities = [ItemEntity::class, LoanEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun itemDAO(): ItemDAO
    abstract fun loanDAO(): LoanDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "myShelf_database",
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}