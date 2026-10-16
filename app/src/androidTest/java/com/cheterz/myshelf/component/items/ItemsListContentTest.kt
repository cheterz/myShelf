package com.cheterz.myshelf.component.items

//import io.qameta.allure.kotlin.Allure
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cheterz.myshelf.common.TestData
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import io.qameta.allure.kotlin.Allure
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
                onAddClick = { _, _, _ -> },
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
                onAddClick = { _, _, _ -> },
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
                onAddClick = { _, _, _ -> },
                onDeleteClick = {},
            )
        }
        Allure.step("Assert: three rows is displayed") {
            composeRule.onAllNodesWithTag(TestTags.ItemsList.ITEM_ROW).assertCountEquals(3)
        }
    }

    @Test
    fun fabClick_opensAlertDialog() {
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = { _, _, _ -> },
                onDeleteClick = {})
        }
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
        Espresso.onView(withId(com.cheterz.myshelf.R.id.titleInput)).check(matches(isDisplayed()))
    }
}