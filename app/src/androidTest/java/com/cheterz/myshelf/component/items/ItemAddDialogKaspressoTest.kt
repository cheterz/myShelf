package com.cheterz.myshelf.component.items

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cheterz.myshelf.R
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ItemAddDialogKaspressoTest : TestCase() {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun dialog_enterData_andSave_callsOnAddClickWithData(): Unit {
        run {
            var capturedTitle: String? = null
            var capturedDescription: String? = null
            var capturedCategory: String? = null

            step("Render items list") {
                composeRule.setContent {
                    ItemsListContent(
                        items = emptyList(), onAddClick = { title, description, category ->
                            capturedTitle = title
                            capturedDescription = description
                            capturedCategory = category
                        },
                        onDeleteClick = {})
                }
            }
            step("Open add item dialog") {
                composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
            }

            step("Enter title & description") {
                Espresso.onView(withId(R.id.titleInput)).perform(typeText("Hammer"))
                Espresso.onView(withId(R.id.descriptionInput)).perform(typeText("Old hammer"))
            }

            step("click SAVE") {
                Espresso.onView(withText("SAVE")).perform(click())
            }

            step("Assert captured data") {
                assertEquals("Hammer", capturedTitle)
                assertEquals("Old hammer", capturedDescription)
                assertEquals("TOOLS", capturedCategory)
            }
        }
    }
}