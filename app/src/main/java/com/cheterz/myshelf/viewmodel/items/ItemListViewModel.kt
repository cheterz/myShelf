package com.cheterz.myshelf.viewmodel.items

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cheterz.myshelf.model.AppDatabase
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.items.ItemEntity
import kotlinx.coroutines.launch

class ItemListViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).itemDAO()

    var items by mutableStateOf<List<Item>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            dao.getAllItems().collect { entities ->
                items = entities.map { entity ->
                    Item(
                        id = entity.id,
                        title = entity.title,
                        description = entity.description,
                        category = entity.category,
                        status = entity.status,
                        imageUri = entity.imageUri
                    )
                }
            }
        }
    }

    fun addFakeItem(){
        viewModelScope.launch {
            dao.insertItem(
                ItemEntity(
                    title = "New thing",
                    category = "OTHER",
                    status = "AVAILABLE"
                )
            )
        }
    }

}