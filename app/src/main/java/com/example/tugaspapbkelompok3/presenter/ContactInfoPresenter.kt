package com.example.tugaspapbkelompok3.presenter

import com.example.tugaspapbkelompok3.model.ContactModel
import com.example.tugaspapbkelompok3.mvp_interface.IContactInfo.*


class ContactInfoPresenter(_view: IContactInfoView): IContactInfoPresenter {
    private var view: IContactInfoView = _view
    private var model: ContactModel = ContactModel()
    private var args = view.getArgs()

    init {
        model.setDB(view.getFragmentContext()!!)
    }

    override fun getName(): String = model.getName().toString()

    override fun getNumber(): String = model.getNumber().toString()

    override fun getEmail(): String = model.getEmail().toString()

    override fun getDescription(): String = model.getDescription().toString()

    override fun viewCreated() {
        val key = args?.getInt("contactID")
        model.getContactById(key)
        view.fillTexts()
    }
}