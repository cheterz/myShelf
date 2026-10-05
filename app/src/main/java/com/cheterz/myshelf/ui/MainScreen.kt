package com.cheterz.myshelf.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.ui.items.ItemListScreen
import com.cheterz.myshelf.ui.loans.LoansListScreen

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Items", "Loans")
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(selectedTab, modifier = Modifier.testTag(TestTags.MainScreen.MAIN_TABS)) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) },
                        modifier = Modifier.testTag(
                            if (index == 0) TestTags.MainScreen.TAB_ITEMS
                            else TestTags.MainScreen.TAB_LOANS
                        )
                    )
                }
            }
            when (selectedTab) {
                0 -> ItemListScreen()
                1 -> LoansListScreen()
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}