package com.nunez.contacts.addContact

import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository

class AddContactInteractor(val repository: ContactsRepository): AddContactContract.Interactor {
    override fun saveContact(contact: Contact) {
        repository.create(contact)
    }
}