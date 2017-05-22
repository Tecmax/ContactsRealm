package com.nunez.contacts.repository

import com.nunez.contacts.entities.Contact


interface ContactsRepositoryTemplate {
    fun create(contact: Contact)

    fun read(contactId: String): Contact

    fun read(): List<Contact>

    fun update(contact: Contact)

    fun delete(contact: Contact)
}