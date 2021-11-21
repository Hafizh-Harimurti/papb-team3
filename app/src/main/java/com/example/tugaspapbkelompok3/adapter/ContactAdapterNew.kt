package com.example.tugaspapbkelompok3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspapbkelompok3.R
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.fragment.ContactInfoFragment

class ContactAdapterNew (private var mContext: Context,
                         private var mContact: List<Contact>,
                         private var isFragment: Boolean = false): RecyclerView.Adapter<ContactAdapterNew.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapterNew.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent,false)

        return ContactAdapterNew.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapterNew.ViewHolder, position: Int) {
        val contact = mContact[position]

        holder.nameTextView.text = contact.name
        holder.emailTextView.text = contact.email
        holder.numberTextView.text = contact.number

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity

                val navController = activity.findNavController(R.id.navHostFragment)
                val bundle = bundleOf("contactID" to contact.contactId)
                navController.navigate(R.id.action_contactListFragment_to_contactInfo2, bundle)

            }
        })
        
    }

    override fun getItemCount(): Int {
        return mContact.size
    }

    class ViewHolder (@NonNull itemView: View): RecyclerView.ViewHolder(itemView){
        var nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        var numberTextView: TextView = itemView.findViewById(R.id.tv_number)
        var emailTextView: TextView = itemView.findViewById(R.id.tv_email)
    }


}