package com.example.tugaspapbkelompok3.mvp_interface

import android.content.Context
import com.example.tugaspapbkelompok3.database.Contact

interface IAddOrEdit {

    interface IAddOrEditModel {
        fun setDB(context: Context)
        fun getContactById(id: Int?)
        fun getName(): String
        fun getNumber(): String
        fun getEmail(): String
        fun getDescription(): String
        fun saveContact(name: String, number: String, email: String, description: String): Pair<String, Int>
        fun deleteContact(id: Int?)
    }

    interface IAddOrEditPresenter {
        fun getIsEditing(): Boolean
        fun setIsEditing(isEditing: Boolean)
        fun getContactById(id: Int?)
        fun getName(): String
        fun getNumber(): String
        fun getEmail(): String
        fun getDescription(): String
        fun saveContact(name: String, number: String, email: String, description: String)
        fun deleteContact(id: Int?)
    }

    interface IAddOrEditView{
        fun getFragmentContext(): Context?
        fun initViewForExistingContact(id: Int)
        fun fillEditTexts()
        fun saveContactResult(result: Pair<String, Int>)
    }
}