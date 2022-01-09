package com.example.grocerylist.UI

import android.os.Build
import android.os.Bundle
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocerylist.Adapter.GroceryAdapter
import com.example.grocerylist.Database.Entity.GroceryItems
import com.example.grocerylist.Database.GroceryDatabase
import com.example.grocerylist.Database.GroceryRepository
import com.example.grocerylist.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var ViewModel: GroceryViewModel
    lateinit var list: List<GroceryItems>



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    val toolbar = findViewById(R.id.toolbar) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)
        toolbar?.setTitle("Grocery List")

        val groceryRepository = GroceryRepository(GroceryDatabase(this))

      val factory =
          GroceryViewModelFactory(groceryRepository)

//         Initialised View Model
        ViewModel = ViewModelProvider(this,factory).get(GroceryViewModel::class.java)

        val groceryAdapter =
            GroceryAdapter(listOf(), ViewModel)


        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = groceryAdapter

//        To display all items in recycler view
        ViewModel.allGroceryItems().observe(this, Observer{
           groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()


     })

//       on ClickListener on button to open dialog box
        btnAdd.setOnClickListener {
           GroceryItemDialog(this,object : DialogListener{
               override fun onAddButtonClicked(item: GroceryItems) {
                   ViewModel.insert(item)
               }
           }).show()
        }


    }


}
