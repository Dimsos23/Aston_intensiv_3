package com.ai.aston_intensive_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ai.aston_intensive_3.MainActivity
import com.ai.aston_intensive_3.R
import com.ai.aston_intensive_3.fragments.EditContact
import com.ai.aston_intensive_3.model.Contact
import com.google.android.material.card.MaterialCardView

class ContactAdapter(
    private val context: Context,
    private var dataset: List<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactId: TextView = view.findViewById(R.id.id_text)
        val contactName: TextView = view.findViewById(R.id.name_text)
        val contactSurname: TextView = view.findViewById(R.id.surname_text)
        val contactPhone: TextView = view.findViewById(R.id.phone_text)
        val cardView: MaterialCardView = view.findViewById(R.id.materialCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contactCurrent = dataset[position]
        val resource = context.resources
        holder.apply {
            contactId.text = resource.getString(R.string.contact_id, contactCurrent.id.toString())
            contactName.text = contactCurrent.name
            contactSurname.text = contactCurrent.surname
            contactPhone.text = resource.getString(R.string.contact_phone, contactCurrent.phone)

            cardView.setOnClickListener {
                val editContactDialog = EditContact(contactCurrent)
                val activity =  itemView.context as MainActivity
                editContactDialog.show(activity.supportFragmentManager, EditContact.TAG)
            }
        }
    }
}