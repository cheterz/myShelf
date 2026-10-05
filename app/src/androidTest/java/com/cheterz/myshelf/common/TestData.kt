package com.cheterz.myshelf.common

import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.loans.Loan

object TestData {
    fun item(
        id: Long = 1,
        title: String = "Hammer",
        description: String? = null,
        category: String = "TOOLS",
        status: String = "HOME",
        imageUri: String? = null,
    ) = Item(id, title, description, category, status, imageUri)

    fun loan(
        id: Long = 1,
        itemId: Long = 1,
        itemTitle: String = "Hammer",
        contact: String = "Oleg",
        loanedDate: Long = 1_000_000L,
        plannedReturnDate: Long? = null,
    ) = Loan(id, itemId, itemTitle, contact, loanedDate, plannedReturnDate)
}