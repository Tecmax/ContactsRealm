package com.nunez.contacts.list

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

    override fun contactCliked(id: String) {
        view.showContactDetails(id)
    }

    override fun contactLongCliked(id: String) {
        view.showDeleteBottomSheet(id)
    }

    override fun showErrorMessage() {
        view.showErrorMessage()
    }

    override fun contactToDeleteClicked(id: String) {
        interactor.deleteContact(id)
        requestContacts()
    }

    override fun addContactClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToAddContactActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToEditContactActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}