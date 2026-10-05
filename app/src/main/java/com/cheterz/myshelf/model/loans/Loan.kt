package com.cheterz.myshelf.model.loans


data class Loan(
    val id: Long,
    val itemId: Long,
    val itemTitle: String,
    val contact: String,
    val loanedDate: Long,
    val plannedReturnDate: Long? = null

)
