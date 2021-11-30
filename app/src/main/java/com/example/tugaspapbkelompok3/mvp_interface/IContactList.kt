package com.example.tugaspapbkelompok3.mvp_interface

import android.content.Context
import com.example.tugaspapbkelompok3.database.Contact

interface IContactList {

    interface IContactListModel {
        fun deleteContact(id: Int?)
    }

    interface IContactListPresenter {
        fun deleteContact(id: Int?)
    }

    interface IContactListView{
        fun getFragmentContext(): Context?
    }
}