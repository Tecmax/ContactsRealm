package com.nunez.contacts.list

import android.content.Context
import com.github.javafaker.Faker
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import rx.Observable
import java.util.concurrent.TimeUnit


class ContactListInteractor(
        val repository: ContactsRepository,
        val context: Context
) : ContactListContract.Interactor {

    override fun addFakeContacts(): List<Contact> {
        val faker = Faker()
        var contacts = ArrayList<Contact>()

        for (i in 0..6) {
            with(faker) {
                val contact = Contact(
                        firstName = name().firstName(),
                        lastName = name().lastName(),
                        zipCode = address().zipCode(),
                        phoneNumber = phoneNumber().cellPhone(),
                        birthday = date().past(10, TimeUnit.DAYS).toString()
                )

                repository.create(contact)
                contacts.add(contact)
            }
        }
        return contacts as List<Contact>
    }

    override fun getContacts(): Observable<List<Contact>> {

        return Observable.create {
            subscriber ->

            var contacts = repository.read()

            if (contacts.isEmpty()) {
              contacts = addFakeContacts()
            }

            subscriber.onNext(contacts)
            subscriber.onCompleted()
        }
    }

    override fun deleteContact(id: String) {
        repository.delete(id)
    }
}