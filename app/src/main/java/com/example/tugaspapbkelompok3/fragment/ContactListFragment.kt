package com.example.tugaspapbkelompok3.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspapbkelompok3.R
import com.example.tugaspapbkelompok3.adapter.ContactAdapterNew
import com.example.tugaspapbkelompok3.database.Contact
import com.example.tugaspapbkelompok3.database.DB
import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit
import com.example.tugaspapbkelompok3.mvp_interface.IContactList
import com.example.tugaspapbkelompok3.presenter.AddOrEditPresenter
import com.example.tugaspapbkelompok3.presenter.ContactListPresenter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ContactListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactListFragment : Fragment(), IContactList.IContactListView {
    // TODO: Rename and change types of parameters
    private var recyclerView: RecyclerView? = null
    private var contactAdapter: ContactAdapterNew? = null
    private lateinit var presenter: IContactList.IContactListPresenter

//    private var mContact: MutableList<Contact>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ContactListPresenter(this)
    }

    override fun onResume() {
        updateList()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contact_list, container, false)
        return view;
    }

    private fun updateList() {
        val db = DB.getDB(requireActivity().applicationContext)
        val contactDAO = db.ContactDAO()
        recyclerView = view?.findViewById(R.id.recycler_view_contact)

        var mContact = contactDAO.getAllContacts()

        contactAdapter = context?.let { ContactAdapterNew(it, mContact as ArrayList<Contact>, true, presenter ) }
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = contactAdapter
    }

    override fun getFragmentContext(): Context? {
        return context
    }


}