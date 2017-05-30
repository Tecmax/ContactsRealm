package com.nunez.contacts.editContact

import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import rx.Observable

class EditContactInteractor(val repository: ContactsRepository) : EditContactContract.Interactor{

    override fun getContactDetails(id: String): Observable<Contact> {
        return Observable.create {
            subscriber ->

            subscriber.onNext(repository.read(id))
            subscriber.onCompleted()
        }
    }

    override fun saveContact(contact: Contact) {
        repository.update(contact)
    }
}