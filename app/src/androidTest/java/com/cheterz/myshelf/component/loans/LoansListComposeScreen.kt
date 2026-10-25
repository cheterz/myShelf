package com.cheterz.myshelf.component.loans

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.cheterz.myshelf.ui.common.TestTags
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class LoansListComposeScreen(
    semanticProvider: SemanticsNodeInteractionsProvider,
) :
    ComposeScreen<LoansListComposeScreen>(semanticsProvider = semanticProvider) {
    val removeButton: KNode = child {
        hasTestTag(TestTags.LoansList.REMOVE_BUTTON)
    }
    val loanRow: KNode = child {
        hasTestTag(TestTags.LoansList.LOAN_ROW)
    }
}