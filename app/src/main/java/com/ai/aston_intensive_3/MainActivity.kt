package com.ai.aston_intensive_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ai.aston_intensive_3.data.Datasource
import com.ai.aston_intensive_3.databinding.ActivityMainBinding
import com.ai.aston_intensive_3.adapter.ContactAdapter
import com.ai.aston_intensive_3.fragments.EditContact
import com.ai.aston_intensive_3.fragments.NewContact
import com.ai.aston_intensive_3.model.Contact

class MainActivity : AppCompatActivity() {
    companion object {
        var myDataset: MutableList<Contact> = Datasource().loadContacts()
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerview.layoutManager = layoutManager
            recyclerview.adapter = ContactAdapter(this@MainActivity, myDataset)
        }

        //        val contactCardView: MaterialCardView = findViewById(R.id.materialCardView)


        binding.addButton.setOnClickListener {
            NewContact().show(supportFragmentManager, NewContact.TAG)
        }

        binding.icEdit.setOnClickListener {
        }

        //        binding.recyclerview.setOnClickListener {
//            EditContact().show(supportFragmentManager, EditContact.TAG)
//        }
//        contactCardView.setOnClickListener {
//            EditContact().show(supportFragmentManager, EditContact.TAG)
//        }

    }
}
