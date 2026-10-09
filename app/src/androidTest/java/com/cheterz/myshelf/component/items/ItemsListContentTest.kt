package com.cheterz.myshelf.component.items

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import com.cheterz.myshelf.common.TestData
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import io.qameta.allure.kotlin.Allure
//import io.qameta.allure.kotlin.Allure
import org.junit.Rule
import org.junit.Test

class ItemsListContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun emptyItems_ShowEmptyItemsListTest() {
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        Allure.step("Assert: empty list") {
            composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(0)
        }
    }

    @Test
    fun oneItem_showsOneRow() {
        composeRule.setContent {
            ItemsListContent(
                items = listOf(
                    TestData.item()
                ),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        Allure.step("Assert: one row is displayed") {
            composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(1)
        }
        Allure.step("Assert: 'Hammer' text is displayed") {
            composeRule.onNodeWithText("Hammer").assertIsDisplayed()
        }
    }

    @Test
    fun threeItems_showsThreeRows() {
        composeRule.setContent {
            ItemsListContent(
                items = listOf(
                    TestData.item(
                        id = 1,
                        title = "Hammer",
                        category = "TOOLS",
                        status = "HOME"
                    ),
                    TestData.item(
                        id = 2,
                        title = "Screwdriver",
                        category = "TOOLS",
                        status = "HOME"
                    ),
                    TestData.item(
                        id = 3,
                        title = "Wrench",
                        category = "TOOLS",
                        status = "HOME"
                    ),
                ),
                onAddClick = {},
                onDeleteClick = {},
            )
        }
        Allure.step("Assert: three rows is displayed") {
            composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(3)
        }
    }
}