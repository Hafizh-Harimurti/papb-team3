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
import com.example.tugaspapbkelompok3.databinding.ActivityMainBinding
import com.example.tugaspapbkelompok3.databinding.FragmentAddOrEditBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddOrEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddOrEditFragment : Fragment() {
    private lateinit var mainActivity : MainActivity
    private var position : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments!=null){
            position = arguments?.getInt("position")!!
        }
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
        getView()?.findViewById<EditText>(R.id.addOrEditContactName)?.setText(arguments?.getString("name"))
        getView()?.findViewById<EditText>(R.id.addOrEditPhone)?.setText(arguments?.getString("number"))
        getView()?.findViewById<EditText>(R.id.addOrEditEmail)?.setText(arguments?.getString("email"))
        getView()?.findViewById<EditText>(R.id.addOrEditDescription)?.setText(arguments?.getString("desc"))
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
        when (item.itemId){
            R.id.menu_save_changes -> {
                if(activity?.currentFocus != null){
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view?.windowToken, 0)
                }
                var newPosition: Int
                var (result, contact) = modifyMainActivityListView(position, mainActivity)
                if(result == "Invalid"){
                    return super.onOptionsItemSelected(item)
                }
                if(position == -1){
                    newPosition = mainActivity.contactArrayList.size
                } else {
                    newPosition = position
                }
                var bundle = bundleOf("position" to newPosition, "name" to contact.name, "email" to contact.email, "number" to contact.number, "desc" to contact.description)
                if(position != -1){
                    navController.navigate(R.id.action_addOrEditFragment_to_contactInfo, bundle)
                } else {
                    navController.navigate(R.id.action_addOrEditFragment_to_blankFragment)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun modifyMainActivityListView(position: Int, mainActivity: MainActivity) : Pair<String, Contact>{
        var contact = Contact(
            getView()?.findViewById<EditText>(R.id.addOrEditContactName)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditPhone)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditEmail)?.text.toString(),
            getView()?.findViewById<EditText>(R.id.addOrEditDescription)?.text.toString())
        if(contact.name == ""){
            Toast.makeText(activity, "Cannot leave name field empty!", Toast.LENGTH_SHORT).show()
            return Pair("Invalid", contact)
        }
        if(position == -1){
            mainActivity.contactArrayList.add(contact)
        } else {
            mainActivity.contactArrayList.set(position, contact)
        }
        mainActivity.lvAdapter.notifyDataSetChanged()
        return Pair("Success", contact)
    }
}