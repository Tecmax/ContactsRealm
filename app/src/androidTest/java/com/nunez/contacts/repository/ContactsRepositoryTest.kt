package com.nunez.contacts.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.nunez.contacts.entities.Contact
import io.realm.Realm
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4::class)
class ContactsRepositoryTest {

    private fun generateContact(): Contact {
        return Contact(
                firstName = "Paulx",
                lastName = "Nunez",
                birthday = "November",
                phoneNumber = "8095852558",
                zipCode = "202104")
    }

    @Before
    fun setUp() {
        Realm.init(InstrumentationRegistry.getContext())
    }

    @Test
    fun create() {
        val contactToCreate = generateContact()
        contactToCreate.id = UUID.randomUUID().toString()
        ContactsRepository().create(contactToCreate)

       val contact = ContactsRepository().read(contactToCreate.id)
        assertEquals("name", contactToCreate.id,contact.id)
    }

    @Test
    fun update() {
        val contact = ContactsRepository().read().get(0)
        contact.firstName = "Em's"

        ContactsRepository().update(contact)

        val updatedContact = ContactsRepository().read(contact.id)
        assertEquals("Name", contact.firstName, updatedContact.firstName)
    }

    @Test
    fun delete() {
        // given
        val previousSize = ContactsRepository().read().size
        val contact = generateContact()
        contact.firstName = "Emma"

        print(previousSize)

        ContactsRepository().create(contact)
        val contacts = ContactsRepository().read()

        // When
        ContactsRepository().delete(contact.id)
        val newSize =  ContactsRepository().read().size

        print(newSize)

        // Then
        assertEquals("Size", previousSize, newSize)
    }

}