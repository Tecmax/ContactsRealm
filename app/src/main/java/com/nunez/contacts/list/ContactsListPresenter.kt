package com.nunez.contacts.list

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class ContactsListPresenter
@Inject constructor(
        val view: ContactListContract.View,
        val interactor: ContactListContract.Interactor
) : ContactListContract.Presenter {

    override fun requestContacts() {
        view.dismissModal()
        interactor.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view.showContacts(it) },
                        { showErrorMessage() }
                )
    }

    override fun contactCliked(id: String) = view.showContactDetails(id)

    override fun contactLongCliked(id: String) = view.showOptionsModalBottomSheet(id)

    override fun showErrorMessage() = view.showErrorMessage()

    override fun contactToDeleteClicked(id: String) {
        interactor.deleteContact(id)
        requestContacts()
    }

    override fun addContactClicked() = goToAddContactActivity()

    override fun goToAddContactActivity() = view.goToAddContactActivity()

    override fun contactToEditClicked(id: String) = view.goToEditContactActivity(id)
}