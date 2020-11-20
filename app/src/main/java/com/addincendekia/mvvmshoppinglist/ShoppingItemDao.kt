package com.addincendekia.mvvmshoppinglist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun get(): LiveData<List<ShoppingItem>>
}