package com.cheterz.myshelf

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.cheterz.myshelf.ui.common.TestTags
import org.junit.Rule
import org.junit.Test

class ItemListScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addingItem_showsNewItemInList() {
        // нажимаем на кнопку "Добавить вещь"
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()

        // проверяем, что на экране появилась новая строка
        composeRule.onNodeWithText("Новая вещь").assertIsDisplayed()
    }

    @Test
    fun removeItem_noNewItemInList() {
        composeRule.onNodeWithText("Interstellar").assertIsDisplayed()
        composeRule.onNodeWithTag(TestTags.ItemsList.REMOVE_BUTTON).performClick()
        composeRule.onNodeWithText("Interstellar").assertIsNotDisplayed()
    }

    @Test
    fun removeAllItemsInList() {
        val count =
            composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).fetchSemanticsNodes().size
        repeat(count) {
            composeRule.onNodeWithTag(TestTags.ItemsList.REMOVE_BUTTON).performClick()
        }
        composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(0)
    }
}