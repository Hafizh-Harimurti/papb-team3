package com.example.tugaspapbkelompok3.presenter

import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit.*
import com.example.tugaspapbkelompok3.model.AddOrEditModel

class AddOrEditPresenter(_view: IAddOrEditView):IAddOrEditPresenter{
    private var view: IAddOrEditView = _view
    private var model: IAddOrEditModel = AddOrEditModel()

    init {
        model.setDB(view.getFragmentContext()!!)
    }

    override fun getContactById(id: Int?) {
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
        }
        view.saveContactResult(model.saveContact(name, number, email, description))
    }
}