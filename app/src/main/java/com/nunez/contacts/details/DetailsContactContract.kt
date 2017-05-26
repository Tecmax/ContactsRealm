package com.nunez.contacts.details

import com.nunez.contacts.entities.Contact
import rx.Observable

interface DetailsContactContract {

    interface View {
        fun showContactDetails(contact: Contact)
        fun goToEditActivity()
        fun close()
    }

    interface Presenter {
        fun getContactDetails(id: String)
        fun onEditClicked()
        fun onDeleteClicked(id: String)
    }

    interface Interactor {
        fun getContactDetails(id: String): Observable<Contact>
        fun deleteContact(id: String)
    }
}