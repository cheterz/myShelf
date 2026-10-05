package com.cheterz.myshelf.viewmodel.loans

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cheterz.myshelf.model.AppDatabase
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.items.ItemDAO
import com.cheterz.myshelf.model.items.ItemEntity
import com.cheterz.myshelf.model.loans.Loan
import com.cheterz.myshelf.model.loans.LoanEntity
import kotlinx.coroutines.launch

class LoanListViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).loanDAO()
    private val itemDAO = AppDatabase.getDatabase(application).itemDAO()

    var loans by mutableStateOf<List<Loan>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            dao.getAllLoansWithItems().collect { entities ->
                loans = entities.map { entity ->
                    Loan(
                        id = entity.loan.id,
                        itemId = entity.item.id,
                        itemTitle = entity.item.title,
                        contact = entity.loan.contact,
                        loanedDate = entity.loan.loanedDate,
                        plannedReturnDate = entity.loan.plannedReturnDate,
                    )
                }
            }
        }
    }

    //        TODO(): не забыть удолить
    fun addFakeLoan() {
        viewModelScope.launch {
            val itemId = itemDAO.insertItem(
                ItemEntity(
                    title = "Hammer",
                    category = "TOOLS",
                    status = "LOANED"
                )
            )
            dao.insertLoan(
                LoanEntity(
                    itemId = itemId,
                    contact = "Oleg",
                    loanedDate = System.currentTimeMillis(),
                    plannedReturnDate = System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000  // +7 дней
                )
            )
        }
    }
}