package com.cheterz.myshelf.model.items

data class Item(
    val id: Long,
    val title: String,
    val description: String? = null,
    val category: String,
    val status: String,
    val imageUri: String? = null

)