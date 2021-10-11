package com.example.tugaspapbkelompok3

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.contactInfoIsiNama).text = arguments?.getString("name")
        view.findViewById<TextView>(R.id.contactInfoIsiNo).text = arguments?.getString("number")
        view.findViewById<TextView>(R.id.contactInfoIsiEmail).text = arguments?.getString("email")
        view.findViewById<TextView>(R.id.contactInfoIsiDesc).text = arguments?.getString("desc")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_add)
        menu.removeItem(R.id.menu_apply)
        menu.findItem(R.id.menu_edit).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }
}