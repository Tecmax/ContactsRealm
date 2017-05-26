package com.nunez.contacts.details

import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import rx.Observable

class DetailsInteractor(val repository: ContactsRepository): DetailsContactContract.Interactor{


    override fun getContactDetails(id: String): Observable<Contact> {
        return Observable.create {
            subscriber->
            subscriber.onNext(repository.read(id))
            subscriber.onCompleted()
        }
    }

    override fun deleteContact(id: String) = repository.delete(id)
}