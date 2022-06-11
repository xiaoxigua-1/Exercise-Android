package com.example.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class B : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val database by lazy { context?.let { MyDatabase.createDatabase(it) } }
        val view = inflater.inflate(R.layout.b, container, false)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val edit = view.findViewById<EditText>(R.id.editTextTextPersonName)
            val username = edit.text.toString()
            database?.dao()?.insert(Test(null, username))
        }

        return view
    }
}