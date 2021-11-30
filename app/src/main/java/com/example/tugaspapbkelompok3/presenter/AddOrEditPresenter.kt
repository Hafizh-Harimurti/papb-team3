package com.example.tugaspapbkelompok3.presenter

import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit.*
import com.example.tugaspapbkelompok3.model.ContactModel

class AddOrEditPresenter(_view: IAddOrEditView):IAddOrEditPresenter{
    private var view: IAddOrEditView = _view
    private var model: ContactModel = ContactModel()
    private var isEditing: Boolean = false

    init {
        model.setDB(view.getFragmentContext()!!)
    }

    override fun getIsEditing(): Boolean {
        return isEditing
    }

    override fun setIsEditing(isEditing: Boolean) {
        this.isEditing = isEditing
    }

    override fun getContactById(id: Int?) {
        if(id != null){
            isEditing = true
        }
        model.getContactById(id)
        view.fillEditTexts()
    }

    override fun getName(): String = model.getName()

    override fun getNumber(): String = model.getNumber()

    override fun getEmail(): String = model.getEmail()

    override fun getDescription(): String = model.getDescription()

    override fun saveContact(name: String, number: String, email: String, description: String) {
        if(name == ""){
            view.saveContactResult(Pair("Name field must be filled", -1))
        } else {
            view.saveContactResult(model.saveContact(name, number, email, description))
        }
    }

    override fun deleteContact(id: Int?) {
        model.deleteContact(id)
    }
}