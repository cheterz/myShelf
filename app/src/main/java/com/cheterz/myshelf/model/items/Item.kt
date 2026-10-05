package com.cheterz.myshelf.model.items

data class Item(
    val id: Long,
    val title: String,
    val category: String,
    val status: String
)
val fakeItems = listOf(
    Item(1,"Dune", "book", status = "AVAILABLE"),
    Item(2,"CATAN", "game", status = "LENT"),
    Item(3,"Interstellar", "movie", status = "AVAILABLE")

)
