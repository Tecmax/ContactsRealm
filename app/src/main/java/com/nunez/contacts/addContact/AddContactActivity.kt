package com.nunez.contacts.addContact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nunez.contacts.R
import com.nunez.contacts.common.inputText
import com.nunez.contacts.common.showSnackbar
import com.nunez.contacts.editContact.DatePickerFragment
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

        birthdayInput.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) presenter.onDateClicked()
        }
        birthdayInput.setOnClickListener { presenter.onDateClicked() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        when (id) {
            R.id.action_save -> {
                with(currentContact) {
                    firstName = firstNameInput.text.toString()
                    lastName = lastNameInput.text.toString()
                    phoneNumber = phoneNumberInput.text.toString()
                    birthday = birthdayInput.text.toString()
                    zipCode = zipcodeInput.text.toString()
                }

                presenter.onSaveClicked(currentContact)
                return true
            }

            R.id.action_discard -> {
                presenter.onCancelClicked()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun closeView() {
        finish()
    }

    override fun showErrorMessage() {
        showSnackbar(container, getString(R.string.add_contact_error_msg))
    }

    override fun showDatePicker() {
        DatePickerFragment({
            dateSelected ->
            birthdayInput.inputText(dateSelected)
        }).show(supportFragmentManager, "datepicker")
    }
}
