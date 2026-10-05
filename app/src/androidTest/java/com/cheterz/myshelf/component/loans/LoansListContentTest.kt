package com.cheterz.myshelf.component.loans

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.cheterz.myshelf.common.TestData
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.loans.LoansListContent
import org.junit.Rule
import org.junit.Test

class LoansListContentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun emptyLoans_showEmptyLoansList() {
        composeRule.setContent {
            LoansListContent(
                loans = emptyList(),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        composeRule.onAllNodesWithTag(TestTags.LoansList.LOAN_ROW).assertCountEquals(0)
    }

    @Test
    fun oneLoan_showOneLoanList() {
        composeRule.setContent {
            LoansListContent(
                loans = listOf(
                    TestData.loan()
                ),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        composeRule.onAllNodesWithTag(TestTags.LoansList.LOAN_ROW).assertCountEquals(1)
        composeRule.onNodeWithText("Hammer").assertIsDisplayed()
    }

    @Test
    fun threeLoans_showThreeLoansList() {
        composeRule.setContent {
            LoansListContent(
                loans = listOf(
                    TestData.loan(id = 1, itemTitle = "Screwdriver"),
                    TestData.loan(id = 2, itemTitle = "Wrench"),
                    TestData.loan(id = 3, itemTitle = "Hammer"),
                ),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        composeRule.onAllNodesWithTag(TestTags.LoansList.LOAN_ROW).assertCountEquals(3)
    }
}