package com.example.tugaspapbkelompok3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugaspapbkelompok3.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val number = intent.getStringExtra("number")
        val description = intent.getStringExtra("description")

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ContactInfo()

        println("name =" + name);
        println("email =" + email);
        println("number =" + number);
        println("description =" + description);
//
//        val bundle = Bundle()
//        bundle.putString("name", name)
////        bundle.putString("email", email)
//
//        fragment.arguments = bundle
//        fragmentTransaction.add(R.id.fragmentContactDetails, fragment).commit()
    }
}