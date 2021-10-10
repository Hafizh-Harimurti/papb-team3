package com.example.tugaspapbkelompok3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.contactListView.adapter = Adapter(this, contactArrayList);
        binding.contactListView.isClickable = true;
        binding.contactListView.setOnItemClickListener{ parent, view, position, id ->
            val name = name[position]
            val email = email[position]
            val number = number[position]
            val description = description[position]

            val i = Intent(this, ContactDetailsActivity::class.java);
            i.putExtra("name", name);
            i.putExtra("email", email);
            i.putExtra("number", number);
            i.putExtra("description", description);
            startActivity(i)
        }

    }
}