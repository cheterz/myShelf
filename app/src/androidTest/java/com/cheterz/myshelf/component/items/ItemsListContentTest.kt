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
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cheterz.myshelf.R
import com.cheterz.myshelf.common.TestData
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import io.qameta.allure.kotlin.Allure
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.hamcrest.CoreMatchers.`is`
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
        Espresso.onView(withId(com.cheterz.myshelf.R.id.titleInput)).inRoot(RootMatchers.isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun dialog_enterData_andSave_callsOnAddClickWithData() {
        var capturedTitle: String? = null
        var capturedDescription: String? = null
        var capturedCategory: String? = null

        composeRule.setContent {
            ItemsListContent(items = emptyList(), onAddClick = { title, description, category ->
                capturedTitle = title
                capturedDescription = description
                capturedCategory = category
            }, onDeleteClick = {})
        }
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
        Espresso.onView(withId(R.id.titleInput)).inRoot(RootMatchers.isDialog())
            .perform(typeText("HAMMER"))
        Espresso.onView(withId(R.id.descriptionInput)).inRoot(RootMatchers.isDialog())
            .perform(typeText("old hammer"))
        Espresso.onView(withText("SAVE")).inRoot(RootMatchers.isDialog()).perform(click())

        assertEquals("HAMMER", capturedTitle)
        assertEquals("old hammer", capturedDescription)
        assertEquals("TOOLS", capturedCategory)
    }

    @Test
    fun dialog_cancel_doesNotCallOnAddClick() {
        var called = false
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = { _, _, _ -> called = true },
                onDeleteClick = {})
        }
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
        Espresso.onView(withText("CANCEL")).inRoot(RootMatchers.isDialog()).perform(click())

        assertFalse(called)
    }

    @Test
    fun dialog_emptyTitle_andSave_doesNotCallOnAddClick() {
        var called = false
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = { _, _, _ -> called = true },
                onDeleteClick = {})
        }
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
        Espresso.onView(withText("SAVE")).inRoot(RootMatchers.isDialog()).perform(click())

        assertFalse(called)
    }

    @Test
    fun dialog_selectCategory_fromSpinner_passesCorrectCategory() {
        var capturedCategory: String? = null
        composeRule.setContent {
            ItemsListContent(
                items = emptyList(),
                onAddClick = { _, _, category -> capturedCategory = category },
                onDeleteClick = {}
            )
        }
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
        Espresso.onView(withId(R.id.titleInput)).inRoot(RootMatchers.isDialog())
            .perform(typeText("Dune"))
        Espresso.onView(withId(R.id.categorySpinner)).inRoot(RootMatchers.isDialog())
            .perform(click())
        Espresso.onData(`is`("BOOKS"))
            .inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
        Espresso.onView(withText("SAVE"))
            .inRoot(RootMatchers.isDialog())
            .perform(click())
        assertEquals("BOOKS", capturedCategory)
    }
}