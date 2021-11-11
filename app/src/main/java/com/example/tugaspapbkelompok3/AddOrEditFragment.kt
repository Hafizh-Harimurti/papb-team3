package com.example.tugaspapbkelompok3

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.DB
import com.example.tugaspapbkelompok3.databinding.ActivityMainBinding
import com.example.tugaspapbkelompok3.databinding.FragmentAddOrEditBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddOrEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddOrEditFragment : Fragment() {
    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_or_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val db = DB.getDB(requireActivity().applicationContext)
        val contactDAO = db.ContactDAO()

        val key = arguments?.getInt("contactID")

        if (key != null) {
            val displayedContact: Contact = contactDAO.getContactById(key!!) as Contact
            getView()?.findViewById<EditText>(R.id.addOrEditContactName)
                ?.setText(displayedContact?.name)
            getView()?.findViewById<EditText>(R.id.addOrEditPhone)
                ?.setText(displayedContact?.number)
            getView()?.findViewById<EditText>(R.id.addOrEditEmail)?.setText(displayedContact?.email)
            getView()?.findViewById<EditText>(R.id.addOrEditDescription)
                ?.setText(displayedContact?.description)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_add)
        menu.removeItem(R.id.menu_edit)
        menu.findItem(R.id.menu_save_changes).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId) {
            R.id.menu_save_changes -> {
                val (result, contact) = modifyContact()
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
                if (result == "invalid input")
                    super.onOptionsItemSelected(item)
                else
                    arguments?.putInt("contactID", contact.contactId)
                    mainActivity.updateList()
                    navController.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun modifyContact(): Pair<String, Contact> {
        var contact = Contact(
            getView()?.findViewById<EditText>(R.id.addOrEditContactName)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditPhone)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditEmail)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditDescription)?.text.toString()
        )
        val db = DB.getDB(requireActivity().applicationContext)
        val contactDAO = db.ContactDAO()

        if (contact.name == "")
            return Pair("invalid input",contact)
        else if (arguments?.getInt("contactID") != null){
            contact.contactId = requireArguments().getInt("contactID")
            contactDAO.updateContact(contact)
            return Pair("contact updated", contact)
        }
        else{
            contactDAO.newContacts(contact)
            return Pair("contact added", contact)
        }
    }

    override fun onDestroyView() {
        mainActivity.hideKeyboard()
        super.onDestroyView()
    }

}
