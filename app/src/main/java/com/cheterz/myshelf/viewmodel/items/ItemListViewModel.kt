package com.cheterz.myshelf.viewmodel.items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheterz.myshelf.model.items.Item
import com.cheterz.myshelf.model.items.ItemDAO
import com.cheterz.myshelf.model.items.ItemEntity
import com.cheterz.myshelf.model.items.ItemStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val dao: ItemDAO,
) : ViewModel() {
    fun loadFromNetwork() {
        viewModelScope.launch {
            try {
                val remoteItems = com.cheterz.myshelf.data.remote.RetrofitClient.api.getItems()
                items = remoteItems
            } catch (e: Exception) {
                Log.d(TAG, "loadFromNetwork: {e}", e)
            }
        }
    }

    var items by mutableStateOf<List<Item>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            loadFromNetwork()
            dao.getAllItems()
                .collect { entities ->
                    items = entities.map { entity ->
                        Item(
                            id = entity.id,
                            title = entity.title,
                            description = entity.description,
                            category = entity.category,
                            status = entity.status, imageUri = entity.imageUri
                        )
                    }
                }
        }
    }

    fun addItem(title: String, description: String?, category: String) {
        viewModelScope.launch {
            dao.insertItem(
                ItemEntity(
                    title = title,
                    description = description,
                    category = category,
                    status = ItemStatus.HOME.name
                )
            )
        }
    }
}