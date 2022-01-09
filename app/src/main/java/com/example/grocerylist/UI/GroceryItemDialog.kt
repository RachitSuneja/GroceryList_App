package com.example.grocerylist.UI

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.grocerylist.Database.Entity.GroceryItems
import com.example.grocerylist.R
import kotlinx.android.synthetic.main.grocerydialog.*

class GroceryItemDialog(context: Context,var dialogListener: DialogListener):AppCompatDialog(context) {

     var  quantity : Int = 0
     var price : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.grocerydialog)

        tvSave.setOnClickListener {

            val name = etItemName.text.toString()
            val quantityString = etItemQuantity.text.toString()
            val priceString = etItemPrice.text.toString()

            if (name.isEmpty()){
                etItemName.error = "Please enter item name"
                return@setOnClickListener
            }

            if(quantityString.isEmpty()){
                etItemQuantity.error = "Please enter valid quantity"
                return@setOnClickListener
            }
            else{
                quantity = etItemQuantity.text.toString().toInt()
            }
             if(priceString.isEmpty()){
                etItemPrice.error = "Please enter valid price"
                 return@setOnClickListener
            }
            else{
                 price = etItemPrice.text.toString().toInt()
             }


            val item = GroceryItems(name , quantity , price)
            dialogListener.onAddButtonClicked(item)
            dismiss()

        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }
}