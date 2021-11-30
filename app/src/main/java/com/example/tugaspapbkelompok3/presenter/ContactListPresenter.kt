package com.example.tugaspapbkelompok3.presenter

import com.example.tugaspapbkelompok3.model.ContactModel
import com.example.tugaspapbkelompok3.mvp_interface.IContactInfo
import com.example.tugaspapbkelompok3.mvp_interface.IContactList

class ContactListPresenter(_view: IContactList.IContactListView): IContactList.IContactListPresenter {
    private var view: IContactList.IContactListView = _view
    private var model: ContactModel = ContactModel()

    init {
        model.setDB(view.getFragmentContext()!!)
    }

    override fun deleteContact(id: Int?) {
        model.deleteContact(id)
    }
}