package com.nunez.contacts.common

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.nunez.contacts.R
import com.nunez.contacts.entities.Contact
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactsAdapter(
        var contacts: List<Contact>,
        var onClickListener: (String) -> Unit,
        var onLongClickListener: (String) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder?, position: Int) {
        holder?.let { it.bind(contacts[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(parent.inflate(R.layout.contact_item),
                onClickListener,
                onLongClickListener)
    }

    class ContactViewHolder(
            val view: View,
            val contactClickListener: (String) -> Unit,
            val contactLongClickListener: (String) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        fun bind(contact: Contact) {
            with(contact) {
                view.contactName.text = "$firstName  $lastName"

                view.isLongClickable = true
                view.setOnClickListener { contactClickListener(id) }
                view.setOnLongClickListener {
                    contactLongClickListener(id)
                    true
                }
            }
        }
    }
}