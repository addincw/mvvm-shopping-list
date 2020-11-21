package com.addincendekia.mvvmshoppinglist.ui.shopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.addincendekia.mvvmshoppinglist.R
import com.addincendekia.mvvmshoppinglist.data.db.ShoppingDatabase
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.addincendekia.mvvmshoppinglist.data.repositories.ShoppingItemRepository
import com.addincendekia.mvvmshoppinglist.others.ShoppingItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel.ShoppingItem as ShoppingItemVm
import com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel.ShoppingItemFactory as ShoppingItemVmFactory

class ShoppingActivity : AppCompatActivity() {
//class ShoppingActivity : AppCompatActivity(), KodeinAware {
//    override val kodein by kodein()
//    private val factory: ShoppingItemVmFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingItemRepository(database)
        val factory = ShoppingItemVmFactory(repository)

        val viewModel = ViewModelProviders
            .of(this, factory)
            .get(ShoppingItemVm::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        val rvShoppingItem = findViewById<RecyclerView>(R.id.rvShoppingItem)
        val fabShoppingItemDialog = findViewById<FloatingActionButton>(R.id.fabAddShoppingItem)

        rvShoppingItem.layoutManager = LinearLayoutManager(this)
        rvShoppingItem.adapter = adapter

        viewModel.all().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fabShoppingItemDialog.setOnClickListener {
            ShoppingItemDialog(
                this,
                object : ShoppingItemDialogListener {
                    override fun onAddShoppingItem(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }
            )
            .show()
        }
    }
}