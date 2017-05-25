package com.nunez.contacts.editContact

import com.nunez.contacts.entities.Contact
import rx.Observable

interface EditContactContract {
    interface View{
        fun showContact(contact: Contact)
        fun closeView()
        fun getCurrentContactDetails(): Contact
    }

    interface Presenter{
        fun getContactDetails(id: String)
        fun onSaveClicked()
        fun onCancelClicked()
    }

    interface Interactor{
        fun getContactDetails(id: String): Observable<Contact>
        fun saveContact(contact: Contact)
    }
}