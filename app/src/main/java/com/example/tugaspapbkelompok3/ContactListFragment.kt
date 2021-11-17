package com.example.tugaspapbkelompok3

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tugaspapbkelompok3.database.DB
import com.example.tugaspapbkelompok3.databinding.FragmentContactListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    private var _binding : FragmentContactListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }
    public fun updateList() {
        val db = DB.getDB(requireActivity().applicationContext)
        val contactDAO = db.ContactDAO()
        var lvAdapter = Adapter(requireActivity(), contactDAO.getAllContacts())
        binding.contactListView.adapter = lvAdapter
        binding.contactListView.setOnItemClickListener { adapterView, view, i, j->
            val clickedContactID = i+1 //TODO: temp
            val bundle = bundleOf("contactID" to clickedContactID)
            findNavController().navigate(R.id.action_blankFragment_to_contactInfo,bundle)
        }
        super.onResume()
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_edit)
        menu.removeItem(R.id.menu_save_changes)
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