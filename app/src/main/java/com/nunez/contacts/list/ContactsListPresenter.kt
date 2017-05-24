package com.nunez.contacts.list

import com.nunez.contacts.editContact.EditContactActivity
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ContactsListPresenter(
        val view: ContactListContract.View,
        val interactor: ContactListContract.Interactor
) : ContactListContract.Presenter {

    override fun requestContacts() {
        interactor.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view.showContacts(it) },
                        { showErrorMessage() }
                )
    }

    override fun contactCliked(id: String) = view.showContactDetails(id)

    override fun contactLongCliked(id: String) = view.showDeleteBottomSheet(id)

    override fun showErrorMessage() = view.showErrorMessage()

    override fun contactToDeleteClicked(id: String) {
        interactor.deleteContact(id)
        requestContacts()
    }

    override fun addContactClicked() = goToAddContactActivity()

    override fun goToAddContactActivity() = view.goToActivity(EditContactActivity::class)

    override fun goToEditContactActivity() {
    }
}