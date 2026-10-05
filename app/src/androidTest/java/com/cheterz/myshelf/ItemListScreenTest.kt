package com.cheterz.myshelf

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import org.junit.Rule
import org.junit.Test

class ItemListScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun emptyItems_ShowEmptyItemsListTest() {
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = { },
                onDeleteClick = { }
            )
        }
        composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(0)
    }

    @Test
    fun oneItem_showsOneRow() {
        composeRule.setContent {
            ItemsListContent(
                items = listOf(
                    Item(id = 1, title = "Hammer", category = "TOOLS", status = "HOME")
                ),
                onAddClick = {},
                onDeleteClick = {}
            )
        }
        composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(1)
        composeRule.onNodeWithText("Hammer").assertIsDisplayed()
    }

    @Test
    fun threeItems_showsThreeRows() {
        composeRule.setContent {
            ItemsListContent(
                items = listOf(
                    Item(id = 1, title = "Hammer", category = "TOOLS", status = "HOME"),
                    Item(id = 2, title = "Screwdriver", category = "TOOLS", status = "HOME"),
                    Item(id = 3, title = "Wrench", category = "TOOLS", status = "HOME")
                ),
                onAddClick = {},
                onDeleteClick = {}
            )
        }
        composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(3)
    }
}