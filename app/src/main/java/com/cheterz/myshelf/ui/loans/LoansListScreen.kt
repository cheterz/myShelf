package com.cheterz.myshelf.ui.loans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cheterz.myshelf.model.loans.Loan
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.common.formatDate
import com.cheterz.myshelf.viewmodel.loans.LoansListViewModel

@Composable
fun LoansListScreen(
    viewModel: LoansListViewModel = viewModel()
) {
    LoansListContent(
        loans = viewModel.loans,
        onAddClick = { viewModel.addFakeLoan() },
//        TODO add remove method
        onDeleteClick = {}
    )
}

@Composable
fun LoansListContent(
    loans: List<Loan>,
    onAddClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(loans) { loan ->
                    LoanRow(loan)
                }
            }

            Button(
                onClick = onDeleteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag(TestTags.LoansList.REMOVE_BUTTON)
            ) {
                Text("Удалить последнее")
            }
        }
    }
}

@Composable
fun LoanRow(loan: Loan) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestTags.LoansList.LOAN_ROW)
    ) {
        Column {
            Text(text = loan.itemTitle, style = MaterialTheme.typography.titleMedium)
            Text(text = loan.contact)
            Text(text = "Отдан: ${formatDate(loan.loanedDate)}")
            Text(text = "Вернуть до: ${formatDate(loan.plannedReturnDate)}")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}