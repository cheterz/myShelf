package com.cheterz.myshelf.ui.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheterz.myshelf.R
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.items.ItemCategory
import com.cheterz.myshelf.ui.common.TestTags
import com.cheterz.myshelf.viewmodel.items.ItemListViewModel


@Composable
fun ItemListScreen(
    viewModel: ItemListViewModel = hiltViewModel(),
) {
    ItemsListContent(
        items = viewModel.items,
        onAddClick = { title, description, category ->
            viewModel.addItem(
                title,
                description,
                category
            )
        },
//        TODO add remove method
        onDeleteClick = {}

    )
}

@Composable
fun ItemsListContent(
    items: List<Item>,
    onAddClick: (title: String, description: String?, category: String) -> Unit,
    onDeleteClick: () -> Unit,
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showAddItemDialog(context) { title, description, category ->
                        onAddClick(title, description, category)
                    }
                },
                modifier = Modifier.testTag(TestTags.ItemsList.ADD_BUTTON)
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
                items(items) { item ->
                    ItemRow(item)
                }
            }

            Button(
                onClick = onDeleteClick,
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
            item.description?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }
            Text(text = item.category)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = item.status)
    }
}

private fun showAddItemDialog(
    context: android.content.Context,
    onConfirm: (title: String, description: String?, category: String) -> Unit,
) {
    val dialogView =
        android.view.LayoutInflater.from(context).inflate(R.layout.dialog_add_item, null)
    val spinner = dialogView.findViewById<android.widget.Spinner>(R.id.categorySpinner)
    val categories = ItemCategory.entries.map { it.name }
    spinner.adapter =
        android.widget.ArrayAdapter(context, android.R.layout.simple_spinner_item, categories)
    android.app.AlertDialog.Builder(context).setTitle("Add item").setView(dialogView)
        .setPositiveButton("SAVE") { dialog, _ ->
            val titleInput = dialogView.findViewById<android.widget.EditText>(R.id.titleInput)
            val descriptionInput =
                dialogView.findViewById<android.widget.EditText>(R.id.descriptionInput)
            val title = titleInput.text.toString()
            if (title.isNotBlank()) {
                onConfirm(
                    title,
                    descriptionInput.text.toString().takeIf { it.isNotBlank() },
                    spinner.selectedItem.toString()
                )
            }
            dialog.dismiss()
        }.setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss() }
        .show()

}