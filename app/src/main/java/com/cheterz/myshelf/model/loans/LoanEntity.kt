package com.cheterz.myshelf.model.loans

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cheterz.myshelf.model.items.ItemEntity

@Entity(
    tableName = "loans",
    foreignKeys = [ForeignKey(
        entity = ItemEntity::class,
        parentColumns = ["id"],
        childColumns = ["itemId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index("itemId")]
)
data class LoanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val itemId: Long,
    val contact: String,
    val loanedDate: Long,
    val plannedReturnDate: Long?
)