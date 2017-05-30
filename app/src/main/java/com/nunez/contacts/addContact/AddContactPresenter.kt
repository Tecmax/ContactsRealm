package com.nunez.contacts.addContact

import com.nunez.contacts.entities.Contact

/**
 * Created by paulnunez on 5/24/17.
 */
class AddContactPresenter(
        val view: AddContactContract.View,
        val interactor: AddContactContract.Interactor
) : AddContactContract.Presenter {

    override fun onCancelClicked() = view.closeView()

    override fun saveContact(contact: Contact) {
        interactor.saveContact(contact)
        view.closeView()
    }

    override fun onSaveClicked(contact: Contact) {

        // really simple verification
        if (contact.firstName.isNotEmpty())
            saveContact(contact)
        else
            view.showErrorMessage()
    }

    override fun onDateClicked() = view.showDatePicker()
}