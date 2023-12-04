package com.ai.aston_intensive_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.aston_intensive_3.data.Datasource
import com.ai.aston_intensive_3.databinding.ActivityMainBinding
import com.ai.aston_intensive_3.adapter.ContactAdapter
import com.ai.aston_intensive_3.fragments.NewContactDialog
import com.ai.aston_intensive_3.model.Contact

class MainActivity : AppCompatActivity() {
    companion object {
        var contactList = ArrayList<Contact>()
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactList = Datasource().loadContacts()
        setAdapter()

        binding.addButton.setOnClickListener {
            NewContactDialog().show(supportFragmentManager, NewContactDialog.TAG)
        }
    }

    private fun setAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerview.apply {
            adapter = contactAdapter
            layoutManager = linearLayoutManager
            contactAdapter.updateData(contactList)
        }
    }
}
