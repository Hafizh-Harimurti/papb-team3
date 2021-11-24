package com.example.tugaspapbkelompok3.mvp_interface
import android.content.Context
import android.os.Bundle

interface IContactInfo {

    interface IContactInfoPresenter {
        fun viewCreated()
        fun getName(): String
        fun getNumber(): String
        fun getEmail(): String
        fun getDescription(): String
    }

    interface IContactInfoView{
        fun getFragmentContext(): Context?
        fun fillTexts()
        fun getArgs(): Bundle?
    }
}
