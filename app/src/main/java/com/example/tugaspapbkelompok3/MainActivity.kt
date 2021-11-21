package com.example.tugaspapbkelompok3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tugaspapbkelompok3.adapter.ContactAdapter
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.DB
import com.example.tugaspapbkelompok3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var contactArrayList : Array<Contact>
    lateinit var lvAdapter : ArrayAdapter<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.navHostFragment)
        val appBArConfig = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBArConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onResume() {
        updateList()
        super.onResume()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        when (item.itemId) {
            R.id.menu_add -> navController.navigate(R.id.action_contactListFragment_to_addOrEditFragment2)
        }
        return super.onOptionsItemSelected(item)
    }
    public fun hideKeyboard(){
        val view = currentFocus
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    public fun updateList() {
        val db = DB.getDB(applicationContext)
        val contactDAO = db.ContactDAO()
        lvAdapter = ContactAdapter(this, contactDAO.getAllContacts())
////        binding.contactListView.adapter = lvAdapter
//        binding.contactListView.setOnItemClickListener { adapterView, view, i, j->
//            val clickedContactID = i+1 //TODO: temp
//            val bundle = bundleOf("contactID" to clickedContactID)
//            findNavController(R.id.navHostFragment).navigate(R.id.action_blankFragment_to_contactInfo,bundle)
//        }
        super.onResume()
    }
}