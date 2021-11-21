package com.example.tugaspapbkelompok3.view

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit.*
import com.example.tugaspapbkelompok3.MainActivity
import com.example.tugaspapbkelompok3.R
import com.example.tugaspapbkelompok3.presenter.AddOrEditPresenter

/**
 * A simple [Fragment] subclass.
 * Use the [AddOrEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddOrEditFragment : Fragment(), IAddOrEditView {
    private lateinit var mainActivity: MainActivity
    private lateinit var presenter: IAddOrEditPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        presenter = AddOrEditPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_or_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val key = arguments?.getInt("contactID")
        presenter.getContactById(key)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.removeItem(R.id.menu_add)
        menu.removeItem(R.id.menu_edit)
        menu.findItem(R.id.menu_save_changes).setVisible(true)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save_changes -> {
                presenter.saveContact(
                    requireView().findViewById<EditText>(R.id.addOrEditContactName).text.toString(),
                    requireView().findViewById<EditText>(R.id.addOrEditPhone).text.toString(),
                    requireView().findViewById<EditText>(R.id.addOrEditEmail).text.toString(),
                    requireView().findViewById<EditText>(R.id.addOrEditDescription).text.toString()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        mainActivity.hideKeyboard()
        super.onDestroyView()
    }

    override fun getFragmentContext(): Context? {
        return context
    }

    override fun initViewForExistingContact(id: Int) {
        presenter.getContactById(id)
    }

    override fun fillEditTexts() {
        requireView().findViewById<EditText>(R.id.addOrEditContactName).setText(presenter.getName())
        requireView().findViewById<EditText>(R.id.addOrEditPhone).setText(presenter.getNumber())
        requireView().findViewById<EditText>(R.id.addOrEditEmail).setText(presenter.getEmail())
        requireView().findViewById<EditText>(R.id.addOrEditDescription).setText(presenter.getDescription())
    }

    override fun saveContactResult(result: Pair<String, Int>) {
        Toast.makeText(activity, result.first, Toast.LENGTH_LONG).show()
        if(result.second != -1){
            arguments?.putInt("contactID",result.second)
            findNavController().popBackStack()
        }
    }

}
