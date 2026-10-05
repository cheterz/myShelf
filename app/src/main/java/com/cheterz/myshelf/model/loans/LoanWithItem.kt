package com.cheterz.myshelf.model.loans

import androidx.room.Embedded
import androidx.room.Relation
import com.cheterz.myshelf.model.items.ItemEntity

data class LoanWithItem(
    @Embedded val loan: LoanEntity,
    @Relation(parentColumn = "itemId", entityColumn = "id")
    val item: ItemEntity
)
