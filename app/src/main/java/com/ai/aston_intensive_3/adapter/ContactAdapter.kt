package com.ai.aston_intensive_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ai.aston_intensive_3.ContactDiffUtilCallback
import com.ai.aston_intensive_3.MainActivity
import com.ai.aston_intensive_3.R
import com.ai.aston_intensive_3.fragments.EditContactDialog
import com.ai.aston_intensive_3.model.Contact
import com.google.android.material.card.MaterialCardView

class ContactAdapter(
    private val context: Context) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contactList = ArrayList<Contact>()

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

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contactCurrent = contactList[position]
        val resource = context.resources
        holder.apply {
            contactId.text = resource.getString(R.string.contact_id, contactCurrent.id.toString())
            contactName.text = contactCurrent.name
            contactSurname.text = contactCurrent.surname
            contactPhone.text = resource.getString(R.string.contact_phone, contactCurrent.phone)

            cardView.setOnClickListener {
                val editContactDialog = EditContactDialog(contactCurrent)
                val activity =  itemView.context as MainActivity
                editContactDialog.show(activity.supportFragmentManager, EditContactDialog.TAG)
            }
        }
    }
    fun updateData(newContactList: ArrayList<Contact>) {
        val diffUtilCallBack = ContactDiffUtilCallback(contactList, newContactList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        contactList.clear()
        contactList.addAll(newContactList)
        diffResult.dispatchUpdatesTo(this)
    }
}
