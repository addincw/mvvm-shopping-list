package com.addincendekia.mvvmshoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem

@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun all(): LiveData<List<ShoppingItem>>
}