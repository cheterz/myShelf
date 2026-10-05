package com.cheterz.myshelf.model.items

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val category: String,
    val status: String,
    val imageUri: String? = null
)