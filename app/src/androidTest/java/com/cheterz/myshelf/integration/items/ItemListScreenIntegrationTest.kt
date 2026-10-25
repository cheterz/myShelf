package com.cheterz.myshelf.integration.items

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cheterz.myshelf.MainActivity
import com.cheterz.myshelf.ui.common.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ItemListScreenIntegrationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun addItem_viaDialog_itemAppearsInList() {
        composeRule.onNodeWithTag(TestTags.ItemsList.ADD_BUTTON).performClick()

        Espresso.onView(withId(com.cheterz.myshelf.R.id.titleInput))
            .inRoot(RootMatchers.isDialog())
            .perform(typeText("Hammer"))

        Espresso.onView(withText("SAVE"))
            .inRoot(RootMatchers.isDialog())
            .perform(click())

        composeRule.onNodeWithText("Hammer").assertIsDisplayed()
    }
}