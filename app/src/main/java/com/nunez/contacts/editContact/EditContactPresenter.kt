package com.nunez.contacts.editContact

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class EditContactPresenter(
        val view: EditContactContract.View,
        val interactor: EditContactContract.Interactor
) : EditContactContract.Presenter {

    override fun getContactDetails(id: String) {
        interactor.getContactDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view.showContact(it) },
                        { // TODO: Handle error
                            view.closeView() }
                )
    }

    override fun onSaveClicked() {
        interactor.saveContact(view.getCurrentContactDetails())
        view.closeView()
    }

    override fun onCancelClicked() {
       view.closeView()
    }
}