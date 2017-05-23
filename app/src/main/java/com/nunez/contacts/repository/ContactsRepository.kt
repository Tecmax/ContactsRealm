package com.nunez.contacts.repository

import com.nunez.contacts.entities.Contact
import com.vicpin.krealmextensions.*
import io.realm.Sort
import java.util.*

class ContactsRepository : ContactsRepositoryTemplate {

    override fun create(contact: Contact) {
        contact.id = UUID.randomUUID().toString()
        contact.create()
    }

    override fun read(contactId: String): Contact {
        val contact = Contact().queryFirst { it.equalTo("id", contactId) }

        contact?.let { return contact }
        return Contact()
    }

    override fun read(): List<Contact> {
        return Contact().querySorted("firstName", Sort.ASCENDING)
    }

    override fun update(contact: Contact) {
        contact.save()
    }

    override fun delete(id: String) {
        Contact().delete { it.equalTo("id", id) }
    }
}