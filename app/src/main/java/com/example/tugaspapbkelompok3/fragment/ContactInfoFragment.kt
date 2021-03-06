package com.example.tugaspapbkelompok3.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tugaspapbkelompok3.R
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.DB
import com.example.tugaspapbkelompok3.mvp_interface.IContactInfo.*
import com.example.tugaspapbkelompok3.presenter.ContactInfoPresenter


/**
 * A simple [Fragment] subclass.
 * Use the [ContactInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactInfoFragment : Fragment(), IContactInfoView {

    private lateinit var presenter: IContactInfoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ContactInfoPresenter(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_contact_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val key = arguments?.getInt("contactID")
        presenter.viewCreated()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_add)
        menu.removeItem(R.id.menu_save_changes)
        menu.findItem(R.id.menu_edit).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        when (item.itemId){
            R.id.menu_edit -> {
                navController.navigate(R.id.action_contactInfo_to_addOrEditFragment, arguments)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun fillTexts() {
        requireView().findViewById<TextView>(R.id.contactInfoIsiNama).text = presenter.getName()
        requireView().findViewById<TextView>(R.id.contactInfoIsiNo).text = presenter.getNumber()
        requireView().findViewById<TextView>(R.id.contactInfoIsiEmail).text = presenter.getEmail()
        requireView().findViewById<TextView>(R.id.contactInfoIsiDesc).text = presenter.getDescription()
    }

    override fun getFragmentContext(): Context? {
        return context
    }

    override fun getArgs(): Bundle? {
        return arguments
    }

}