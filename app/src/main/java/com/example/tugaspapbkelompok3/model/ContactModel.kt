package com.example.tugaspapbkelompok3.model

import android.content.Context
import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit.*
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.ContactDAO
import com.example.tugaspapbkelompok3.database.DB

class ContactModel {
    private var contactDAO: ContactDAO? = null

    private var contact: Contact? = null

     fun setDB(context: Context) {
        val db = DB.getDB(context)
        contactDAO = db.ContactDAO()
    }

     fun getContactById(id: Int?) {
        contact = if(id == null){
            Contact("","","","", -1)
        } else{
            contactDAO!!.getContactById(id)
        }
    }

     fun getName(): String = contact!!.name

     fun getNumber(): String = contact!!.number

     fun getEmail(): String = contact!!.email

     fun getDescription(): String = contact!!.description

     fun saveContact(name: String, number: String, email: String, description: String): Pair<String, Int> {
        contact!!.name = name
        contact!!.number = number
        contact!!.email = email
        contact!!.description = description
        return try {
            if (contact!!.contactId != -1) {
                contactDAO!!.updateContact(contact!!)
                Pair("Contact updated!", contact!!.contactId)
            } else {
                contact!!.contactId = 0
                Pair("Contact added!", contactDAO!!.newContacts(contact!!).toInt())
            }
        } catch (e: Exception) {
            Pair("Error: $e", -1)
        }
    }

}