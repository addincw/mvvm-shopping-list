package com.addincendekia.mvvmshoppinglist.ui.shopping

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.addincendekia.mvvmshoppinglist.R
import com.addincendekia.mvvmshoppinglist.data.db.entities.ShoppingItem

class ShoppingItemDialog(context: Context, var dialogListener: ShoppingItemDialogListener): AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_item_dialog)
        setTitle("New Shopping Item")

        val etName: EditText = findViewById(R.id.etName)!!
        val etAmount: EditText = findViewById(R.id.etAmount)!!
        val actSave: TextView = findViewById(R.id.tvSave)!!
        val actCancel: TextView = findViewById(R.id.tvCancel)!!

        actSave.setOnClickListener {
            if(etName.text.toString().isEmpty() || etAmount.text.toString().isEmpty()){
                Toast
                    .makeText(context, "Name and amount cannot be blank", Toast.LENGTH_SHORT)
                    .show()

                return@setOnClickListener
            }

            val newItem = ShoppingItem(etName.text.toString(), etAmount.text.toString().toInt())

            dialogListener.onAddShoppingItem(newItem)
            dismiss()
        }
        actCancel.setOnClickListener {
            cancel()
        }

    }
}

interface ShoppingItemDialogListener {
    fun onAddShoppingItem(item: ShoppingItem)
}