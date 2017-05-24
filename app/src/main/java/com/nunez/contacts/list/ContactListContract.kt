package com.nunez.contacts.list

import android.content.Intent
import com.nunez.contacts.entities.Contact
import rx.Observable


interface ContactListContract {

    interface View {
        fun showContacts(contacts: List<Contact>)
        fun showContactDetails(id: String)
        fun showErrorMessage()
        fun showDeleteBottomSheet(id: String)
        fun goToActivity(intent: Intent)
    }

    interface Presenter {
        fun requestContacts()
        fun contactCliked(id: String)
        fun addContactClicked()
        fun contactLongCliked(id: String)
        fun contactToDeleteClicked(id: String)
        fun showErrorMessage()
        fun goToAddContactActivity()
        fun goToEditContactActivity()
    }

    interface Interactor {
        fun getContacts(): Observable<List<Contact>>
        fun deleteContact(id: String)
        fun addFakeContacts(): List<Contact>
    }
}