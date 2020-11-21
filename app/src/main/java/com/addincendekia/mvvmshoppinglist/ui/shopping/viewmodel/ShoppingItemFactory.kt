package com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.addincendekia.mvvmshoppinglist.data.repositories.ShoppingItemRepository

@Suppress("UNCHECKED_CAST")
class ShoppingItemFactory(
    private val repository: ShoppingItemRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingItem(repository) as T
    }

}