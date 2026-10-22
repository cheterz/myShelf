package com.cheterz.myshelf.di

import android.content.Context
import androidx.room.Room
import com.cheterz.myshelf.model.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            "myShelf_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideItemDAO(database: AppDatabase) = database.itemDAO()

    @Provides
    fun provideLoanDAO(database: AppDatabase) = database.loanDAO()
}