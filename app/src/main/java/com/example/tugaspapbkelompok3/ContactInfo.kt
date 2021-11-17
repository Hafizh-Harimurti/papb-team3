package com.example.tugaspapbkelompok3

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.DB


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
        val db = DB.getDB(requireActivity().applicationContext)
        val contactDAO = db.ContactDAO()

        val key = arguments?.getInt("contactID")

        val displayedContact: Contact = contactDAO.getContactById(key!!) as Contact

        view.findViewById<TextView>(R.id.contactInfoIsiNama).text = displayedContact.name
        view.findViewById<TextView>(R.id.contactInfoIsiNo).text = displayedContact?.number
        view.findViewById<TextView>(R.id.contactInfoIsiEmail).text = displayedContact?.email
        view.findViewById<TextView>(R.id.contactInfoIsiDesc).text = displayedContact?.description
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_save_changes)
        menu.removeItem(R.id.menu_edit)
        menu.findItem(R.id.menu_add).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId) {
            R.id.menu_add -> navController.navigate(R.id.action_blankFragment_to_addOrEditFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}