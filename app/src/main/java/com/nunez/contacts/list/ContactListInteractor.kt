package com.nunez.contacts.list

import android.content.Context
import com.github.javafaker.Faker
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import rx.Observable


class ContactListInteractor(
        val repository: ContactsRepository,
        val context: Context
) : ContactListContract.Interactor {

    override fun addFakeContacts() {
        val faker = Faker()
        for (i in 0..6) {
            with(faker){
                val contact = Contact(
                        firstName = name().firstName(),
                        lastName =  name().lastName(),
                        zipCode = address().zipCode(),
                        phoneNumber = phoneNumber().toString(),
                        birthday = date().toString()
                )

                repository.create(contact)
            }
        }
    }

    override fun getContacts(): Observable<List<Contact>> {

        return Observable.create {
            subscriber ->

            if (repository.read().isEmpty()) {
                addFakeContacts()
            }

            subscriber.onNext(repository.read())
            subscriber.onCompleted()
        }
    }

    override fun deleteContact(id: String) {
        repository.delete(id)
    }
}