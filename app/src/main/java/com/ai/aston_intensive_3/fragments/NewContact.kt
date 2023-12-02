package com.ai.aston_intensive_3.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.ai.aston_intensive_3.MainActivity
import com.ai.aston_intensive_3.R
import com.ai.aston_intensive_3.adapter.ContactAdapter
import com.ai.aston_intensive_3.model.Contact


class NewContact : DialogFragment() {

    private lateinit var adapter: ContactAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout = layoutInflater.inflate(R.layout.fragment_new_contact, null)

            val dataset = MainActivity.myDataset
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerview)
            adapter = ContactAdapter(requireContext(), dataset)
            recyclerView.adapter = adapter


            val addButton: Button = layout.findViewById(R.id.addButton)
            val cancelButton: Button = layout.findViewById(R.id.cancelButton)
            val editTextFirstName: EditText = layout.findViewById(R.id.editTextFirstName)
            val editTextLastName: EditText = layout.findViewById(R.id.editTextLastName)
            val editTextPhone: EditText = layout.findViewById(R.id.editTextPhone)


            addButton.setOnClickListener {
                val id = dataset.size
                val name = editTextFirstName.text.toString()
                val surname = editTextLastName.text.toString()
                val phone = editTextPhone.text.toString()

                val contact = Contact(id, name, surname, phone)
                dataset.add(contact)
                adapter.notifyItemInserted(dataset.size - 1)
                dialog?.cancel()
    }

            cancelButton.setOnClickListener {
                dialog?.cancel()
            }

            builder.setView(layout)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "NewContactDialog"
    }
}
