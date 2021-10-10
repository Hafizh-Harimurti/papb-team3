package com.example.tugaspapbkelompok3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Use the [ContactInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactInfo : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var myTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_contact_info, container, false)
        myTextView = view.findViewById<View>(R.id.contactInfoIsiNama) as TextView

        val bundle = arguments
////        val message = bundle!!.getString("name")
//        println("=============")
//        print(bundle)
        myTextView.text = "hmm"
        return view
    }

}