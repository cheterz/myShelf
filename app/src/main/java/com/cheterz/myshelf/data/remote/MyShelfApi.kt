package com.cheterz.myshelf.data.remote

import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.loans.Loan
import retrofit2.http.GET

interface MyShelfApi {
    @GET("items")
    suspend fun getItems(): List<Item>

    @GET("loans")
    suspend fun getLoans(): List<Loan>
}