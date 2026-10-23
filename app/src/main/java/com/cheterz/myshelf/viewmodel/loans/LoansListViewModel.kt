package com.cheterz.myshelf.viewmodel.loans

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheterz.myshelf.model.items.ItemDAO
import com.cheterz.myshelf.model.items.ItemEntity
import com.cheterz.myshelf.model.loans.Loan
import com.cheterz.myshelf.model.loans.LoanDAO
import com.cheterz.myshelf.model.loans.LoanEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoansListViewModel @Inject constructor(
    private val dao: LoanDAO,
    private val itemDAO: ItemDAO,
) : ViewModel() {
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
                        plannedReturnDate = entity.loan.plannedReturnDate
                    )
                }
            }
        }
    }

    fun addLoan() {
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
                    plannedReturnDate = null
                )
            )
        }
    }
}