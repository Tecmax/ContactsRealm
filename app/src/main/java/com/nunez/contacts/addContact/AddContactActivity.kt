package com.nunez.contacts.addContact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nunez.contacts.R
import com.nunez.contacts.common.showSnackbar
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.add_contact_activity.*

class AddContactActivity : AppCompatActivity(), AddContactContract.View {

    var currentContact = Contact()
    val interactor: AddContactContract.Interactor by lazy { AddContactInteractor(ContactsRepository()) }
    val presenter: AddContactContract.Presenter by lazy { AddContactPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_contact_activity)

        supportActionBar?.title = getString(R.string.add_contact_activity_title)

        saveBtn.setOnClickListener {
            with(currentContact){
                firstName = firstNameInput.text.toString()
                lastName = lastNameInput.text.toString()
                phoneNumber = phoneNumberInput.text.toString()
                birthday = birthdayInput.text.toString()
                zipCode = zipcodeInput.text.toString()
            }

            presenter.onSaveClicked(currentContact)
        }
        cancelBtn.setOnClickListener { presenter.onCancelClicked() }
    }

    override fun closeView() {
        finish()
    }

    override fun showErrorMessage() {
       showSnackbar(container, getString(R.string.add_contact_error_msg))
    }
}
