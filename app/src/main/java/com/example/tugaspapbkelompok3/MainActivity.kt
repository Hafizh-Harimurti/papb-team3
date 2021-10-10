package com.example.tugaspapbkelompok3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.navArgument
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tugaspapbkelompok3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var contactArrayList : ArrayList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = arrayOf(
            "Harits Rizkal Aliamdy",
            "Wahyu",
            "Hafizh"
        )

        val email = arrayOf(
            "haritsrizkal@mail.ugm.ac.id",
            "wahyu@gmail.com",
            "hafizh@gmail.com"
        )

        val number = arrayOf(
            "081224212955",
            "081314422122",
            "081234521241"
        )

        val description = arrayOf(
            "description rizkal",
            "description wahyu",
            "description hafizh"
        )

        contactArrayList = ArrayList()

        for (i in name.indices){
            val contact = Contact(name[i], number[i], email[i], description[i])
            contactArrayList.add(contact)
        }

        binding.contactListView.adapter = Adapter(this, contactArrayList)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navCOntroller = findNavController(R.id.navHostFragment)
        when (item.itemId){
            R.id.menu_add -> navCOntroller.navigate(R.id.action_blankFragment_to_contactInfo)
            R.id.menu_edit -> navCOntroller.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}