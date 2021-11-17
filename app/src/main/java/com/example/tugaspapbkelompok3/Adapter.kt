package com.example.tugaspapbkelompok3

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tugaspapbkelompok3.database.Contact

class Adapter(private val context : Activity, private val arrayList: List<Contact>): ArrayAdapter<Contact>
    (context, R.layout.list_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater : LayoutInflater = LayoutInflater.from(context);
        val view : View = inflater.inflate(R.layout.list_item, null);

        val name : TextView = view.findViewById(R.id.tv_name);
        val number : TextView = view.findViewById(R.id.tv_number);
        val email : TextView = view.findViewById(R.id.tv_email);

        name.text = arrayList[position].name;
        number.text = arrayList[position].number;
        email.text = arrayList[position].email;

        return view
    }

}