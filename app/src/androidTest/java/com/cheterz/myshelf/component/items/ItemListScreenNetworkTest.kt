package com.cheterz.myshelf.component.items

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.cheterz.myshelf.MainActivity
import com.cheterz.myshelf.di.TestNetworkModule
import com.cheterz.myshelf.testdata.MockScenario
import com.cheterz.myshelf.ui.common.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.qameta.allure.kotlin.Allure.step
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ItemListScreenNetworkTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        TestNetworkModule.baseUrl = mockWebServer.url("/").toString()
        hiltRule.inject()
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun loadItems_fromNetwork_showsInList() {
        mockWebServer.enqueue(MockScenario.ITEMS_SUCCESS.toResponse())
        composeRule.onNodeWithText("Mocked Book").assertIsDisplayed()
    }

    @Test
    fun loadItems_EmptyList() {
        step {
            mockWebServer.enqueue(MockScenario.ITEMS_EMPTY.toResponse())
            composeRule.onNodeWithTag(TestTags.ItemsList.ITEM_ROW).assertIsNotDisplayed()
        }
    }
}