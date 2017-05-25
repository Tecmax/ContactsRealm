package com.nunez.contacts.addContact

import com.nunez.contacts.entities.Contact

/**
 * Created by paulnunez on 5/24/17.
 */
interface AddContactContract {

    interface View {
        fun closeView()
        fun showErrorMessage()
    }

    interface Presenter {
        fun onSaveClicked(contact: Contact)
        fun onCancelClicked()
        fun saveContact(contact: Contact)
    }

    interface Interactor {
        fun saveContact(contact: Contact)
    }

}