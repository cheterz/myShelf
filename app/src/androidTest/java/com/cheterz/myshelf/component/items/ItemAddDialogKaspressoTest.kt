package com.cheterz.myshelf.component.items

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemsListContent
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class ItemAddDialogKaspressoTest : TestCase(
    kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport()
) {
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
                        items = emptyList(),
                        onAddClick = { title, description, category ->
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

    @Test
    fun dialog_cancel_doesNotCallOnAddClick(): Unit {
        run {
            var called = false
            step("Render items list content") {
                composeRule.setContent {
                    ItemsListContent(
                        items = emptyList(),
                        onAddClick = { _, _, _ -> called = true },
                        onDeleteClick = {})
                }
            }
            step("open add item dialog") {
                composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
            }
            step("Click CANCEL") {
                Espresso.onView(withText("CANCEL")).perform(click())
            }
            step("Assert addOnClick not performed") {
                assertFalse(called)
            }
        }
    }

    @Test
    fun dialog_emptyTitle_andSave_doesNotCallOnAddClick(): Unit {
        run {
            var called = false
            step("render itemsList content") {
                composeRule.setContent {
                    ItemsListContent(
                        items = emptyList(),
                        onAddClick = { _, _, _ -> called = true },
                        onDeleteClick = {})
                }
            }
            step("Open add item dialog") {
                composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
            }
            step("Try to click SAVE button") {
                Espresso.onView(withText("SAVE")).perform(click())
            }
            step("Assert SAVE call") {
                assertFalse(called)
            }
        }
    }

    @Test
    fun dialog_selectCategory_viaKSpinner_passesCorrectCategory(): Unit {
        run {
            var capturedCategory: String? = null
            step("Render itemsListContent") {
                composeRule.setContent {
                    ItemsListContent(items = emptyList(), onAddClick = { _, _, category ->
                        capturedCategory = category
                    }, onDeleteClick = {})
                }
            }
            step("click on AddItemDialogButton") {
                composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()
            }
            step("Enter title") {
                Espresso.onView(withId(R.id.titleInput)).perform(typeText("Dune"))

            }
            step("Open category spinner") {
                Espresso.onView(withId(R.id.categorySpinner)).perform(click())
            }
            step("select BOOKS category") {
                Espresso.onData(`is`("BOOKS")).inRoot(RootMatchers.isPlatformPopup())
                    .perform(click())
            }
            step("click SAVE button") {
                Espresso.onView(withText("SAVE")).perform(click())
            }
            step("Assert category") {
                assertEquals("BOOKS", capturedCategory)
            }
        }
    }

    @Test
    fun dialog_openViaComposeScreen(): Unit = run {
        step("Render ItemsListContent") {
            composeRule.setContent {
                ItemsListContent(
                    items = emptyList(),
                    onAddClick = { _, _, _ -> },
                    onDeleteClick = {})
            }
        }
        step("Click FAB via composeScreen") {
            onComposeScreen<ItemsListComposeScreen>(composeRule) {
                addButton {
                    assertIsDisplayed()
                    performClick()
                }
            }
        }
        step("Assert dialog is opened") {
            Espresso.onView(withId(R.id.titleInput)).check(matches(isDisplayed()))
        }
    }
}