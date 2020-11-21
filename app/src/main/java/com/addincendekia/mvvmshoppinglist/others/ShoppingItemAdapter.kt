package com.addincendekia.mvvmshoppinglist.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.addincendekia.mvvmshoppinglist.R
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.addincendekia.mvvmshoppinglist.ui.shopping.viewmodel.ShoppingItem as ShoppingItemVm

class ShoppingItemAdapter (
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingItemVm
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingItemViewHolder>() {

    inner class ShoppingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.shopping_item_card, parent, false)

        return ShoppingItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        var tvItemName = holder.itemView.findViewById<TextView>(R.id.tvItemName)
        var tvItemAmount = holder.itemView.findViewById<TextView>(R.id.tvItemAmount)
        var actItemDelete = holder.itemView.findViewById<TextView>(R.id.tvDelete)
        var actItemAdd = holder.itemView.findViewById<ImageView>(R.id.ivAdd)
        var actItemMinus = holder.itemView.findViewById<ImageView>(R.id.ivMinus)

        tvItemName.text = currentShoppingItem.name
        tvItemAmount.text = currentShoppingItem.amount.toString()

        actItemDelete.setOnClickListener { viewModel.delete(currentShoppingItem) }
        actItemAdd.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        actItemMinus.setOnClickListener {
            if(currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    override fun getItemCount() = items.size
}