package com.example.tugaspapbkelompok3

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [ContactInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactInfo : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_contact_info, container, false)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_add)
        menu.findItem(R.id.menu_edit).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }
}