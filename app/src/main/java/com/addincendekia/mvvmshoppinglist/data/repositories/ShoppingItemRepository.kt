package com.addincendekia.mvvmshoppinglist.data.repositories

import com.addincendekia.mvvmshoppinglist.data.db.ShoppingDatabase
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem

class ShoppingItemRepository(private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingItemDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingItemDao().delete(item)

    fun all() = db.getShoppingItemDao().all()
}