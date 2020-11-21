package com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel

import androidx.lifecycle.ViewModel
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.addincendekia.mvvmshoppinglist.data.repositories.ShoppingItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingItem(private val repository: ShoppingItemRepository): ViewModel() {
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch { repository.upsert(item) }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch { repository.delete(item) }

    fun all() = repository.all()
}