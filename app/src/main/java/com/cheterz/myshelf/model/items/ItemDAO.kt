package com.cheterz.myshelf.model.items


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<ItemEntity>>

    @Insert
    suspend fun insertItem(item: ItemEntity)

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Update
    suspend fun updateItem(item: ItemEntity)
}