package com.cheterz.myshelf.component.items

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import com.cheterz.myshelf.ui.common.TestTags
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class ItemsListComposeScreen(semanticProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<ItemsListComposeScreen>(semanticsProvider = semanticProvider) {
    val addButton: KNode = child {
        hasTestTag(TestTags.ItemsList.ADD_BUTTON)
    }
    val removeButton: KNode = child {
        hasTestTag(TestTags.ItemsList.REMOVE_BUTTON)
    }
}