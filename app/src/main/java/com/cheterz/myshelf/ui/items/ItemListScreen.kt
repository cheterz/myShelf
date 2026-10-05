package com.cheterz.myshelf.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.viewmodel.items.ItemListViewModel


@Composable
fun ItemListScreen(
    viewModel: ItemListViewModel = viewModel()
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.addFakeItem() }
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(viewModel.items) { item ->
                    ItemRow(item)
                }
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag(TestTags.ItemsList.REMOVE_BUTTON)
            ) {
                Text("Удалить последнее")
            }
        }
    }
}


@Composable
fun ItemRow(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestTags.ItemsList.ITEM_ROW)
    ) {
        Column {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.category)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = item.status)
    }
}