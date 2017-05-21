package com.nunez.contacts.repository

import com.nunez.contacts.entities.Contact
import com.vicpin.krealmextensions.delete
import com.vicpin.krealmextensions.queryFirst
import com.vicpin.krealmextensions.querySorted
import com.vicpin.krealmextensions.save
import io.realm.Sort

class ConcactsRepository : ContactsRepositoryTemplate {

    override fun create(contact: Contact) {
        contact.save()
    }

    override fun read(contactId: Int): Contact {
        val contact = Contact().queryFirst { it.equalTo("id", contactId) }

        contact?.let { return contact }
        return Contact()
    }

    override fun read(): List<Contact> {
        return Contact().querySorted("name", Sort.DESCENDING)
    }

    override fun update(contact: Contact) {
        contact.save()
    }

    override fun delete(contact: Contact) {
        Contact().delete { it.equalTo("id", contact.id) }
    }
}