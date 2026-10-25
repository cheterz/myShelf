package com.cheterz.myshelf.di

import android.content.Context
import androidx.room.Room
import com.cheterz.myshelf.model.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {
    @Provides
    @Singleton
    fun provideInMemoryDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideItemDao(database: AppDatabase) = database.itemDAO()

    @Provides
    fun provideLoadDao(database: AppDatabase) = database.loanDAO()
}