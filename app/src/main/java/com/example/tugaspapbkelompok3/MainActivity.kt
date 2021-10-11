package com.example.tugaspapbkelompok3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
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

        binding.contactListView.setOnItemClickListener { adapterView, view, i, j->
            val nameValue = view.findViewById<TextView>(R.id.tv_name).text.toString()
            val emailValue = view.findViewById<TextView>(R.id.tv_email).text.toString()
            val numberValue = view.findViewById<TextView>(R.id.tv_number).text.toString()
            val descValue = description[name.indexOf(nameValue)]//temp?
            val bundle = bundleOf("name" to nameValue, "email" to emailValue, "number" to numberValue, "desc" to descValue)
            findNavController(R.id.navHostFragment).navigate(R.id.action_blankFragment_to_contactInfo,bundle)

        }

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
            R.id.menu_add ->
                navCOntroller.navigate(R.id.action_blankFragment_to_addOrEditFragment)
            R.id.menu_edit -> {
                val nameValue = findViewById<TextView>(R.id.contactInfoIsiNama).text.toString()
                val numberValue = findViewById<TextView>(R.id.contactInfoIsiNo).text.toString()
                val emailValue = findViewById<TextView>(R.id.contactInfoIsiNo).text.toString()
                val descValue = findViewById<TextView>(R.id.contactInfoIsiDesc).text.toString()
                val bundle = bundleOf("name" to nameValue, "email" to emailValue, "number" to numberValue, "desc" to descValue)
                navCOntroller.navigate(R.id.action_contactInfo_to_addOrEditFragment, bundle)
            }
            R.id.menu_apply ->
                navCOntroller.navigate(R.id.action_addOrEditFragment_to_blankFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}