package com.example.tugaspapbkelompok3.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.tugaspapbkelompok3.mvp_interface.IAddOrEdit.*
import com.example.tugaspapbkelompok3.MainActivity
import com.example.tugaspapbkelompok3.R
import com.example.tugaspapbkelompok3.presenter.AddOrEditPresenter
import com.example.tugaspapbkelompok3.util.sendNotification

class AddOrEditFragment : Fragment(), IAddOrEditView {
    private lateinit var mainActivity: MainActivity
    private lateinit var presenter: IAddOrEditPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        setHasOptionsMenu(true)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this){
                presenter.setIsEditing(false)
                this.isEnabled = true
                findNavController().popBackStack()
            }
    }

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
            android.R.id.home -> {
                presenter.setIsEditing(false)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onPause(){
        if(arguments?.getInt("contactID") != null && !requireActivity().lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)){
            createChannel(
                getString(R.string.unfinished_edit_channel_id),
                "Edit Page"
            )
            val notificationManager = ContextCompat.getSystemService(
                requireContext(),
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.sendNotification(
                "You may want to continue editing",
                requireContext()
            )
        }
        super.onPause()
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
            presenter.setIsEditing(false)
            findNavController().popBackStack()
        }
    }

    private fun createChannel(channelID: String, channelName: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )

            notificationChannel.description = "Continue editing contact"

            val notificationManager = requireActivity().getSystemService(
                NotificationManager :: class.java
            )
            notificationManager.createNotificationChannel(notificationChannel )
        }
    }
}
