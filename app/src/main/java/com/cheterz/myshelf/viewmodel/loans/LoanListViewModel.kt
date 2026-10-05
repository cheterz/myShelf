package com.cheterz.myshelf.viewmodel.loans

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cheterz.myshelf.model.AppDatabase
import com.cheterz.myshelf.model.loans.Loan
import com.cheterz.myshelf.model.loans.LoanEntity
import kotlinx.coroutines.launch

class LoanListViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).loanDAO()

    var loans by mutableStateOf<List<Loan>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            dao.getAllLoans().collect { entities ->
                loans = entities.map { entity ->
                    Loan(
                        id = entity.id,
                        name = entity.name,
                        contact = entity.contact,
                        date = entity.date
                    )
                }
            }
        }
    }

    fun addFakeLoan() {
        viewModelScope.launch {
            dao.insertLoan(
                LoanEntity(
                    name = "Oleg",
                    contact = "+9123412342134",
                    date = System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000
                )
            )
        }
    }
}