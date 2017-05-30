package com.nunez.contacts.details

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailsPresenter(
        val view: DetailsContactContract.View,
        val interactor: DetailsContactContract.Interactor

) : DetailsContactContract.Presenter {


    override fun getContactDetails(id: String) {
        interactor.getContactDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { view.showContactDetails(it) }
    }

    override fun onEditClicked() = view.goToEditActivity()

    override fun onDeleteClicked(id: String) {
        interactor.deleteContact(id)
        view.close()
    }

}