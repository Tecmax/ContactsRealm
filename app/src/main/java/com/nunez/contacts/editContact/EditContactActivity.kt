package com.nunez.contacts.editContact

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nunez.contacts.R
import com.nunez.contacts.common.inputText
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.list.ContactsListActivity
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.edit_contact_layout.*

class EditContactActivity() : AppCompatActivity(), EditContactContract.View {

    companion object {
        const val EXTRA_CONTACT_ID = "contact_id"
    }

    lateinit var currentContact: Contact
    val interactor: EditContactContract.Interactor by lazy { EditContactInteractor(ContactsRepository()) }
    val presenter: EditContactContract.Presenter by lazy { EditContactPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_contact_layout)

        supportActionBar?.let {
            with(it) {
                title = getString(R.string.edit_contact_activity_title)
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(applicationContext
                        .resources.getDrawable(R.drawable.ic_close))
            }
        }

        val arguments = intent.extras
        arguments?.let {
            val id = it.getString(ContactsListActivity.EXTRA_CONTACT_ID)
            presenter.getContactDetails(id)
        }

        birthdayInput.setOnClickListener { presenter.onDateClicked() }
        birthdayInput.setOnFocusChangeListener {
            v, hasFocus ->
            if (hasFocus) presenter.onDateClicked()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        when (id) {
            R.id.action_save -> presenter.onSaveClicked()
            R.id.action_discard,
            android.R.id.home -> presenter.onCancelClicked()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showContact(contact: Contact) {
        currentContact = contact
        with(contact) {
            firstNameInput.inputText(firstName)
            lastNameInput.inputText(lastName)
            phoneNumberInput.inputText(phoneNumber)
            birthdayInput.inputText(birthday)
            zipcodeInput.inputText(zipCode)
        }
    }

    override fun closeView() {
        finish()
    }

    override fun getCurrentContactDetails(): Contact {
        with(currentContact) {
            firstName = firstNameInput.text.toString()
            lastName = lastNameInput.text.toString()
            phoneNumber = phoneNumberInput.text.toString()
            birthday = birthdayInput.text.toString()
            zipCode = zipcodeInput.text.toString()
        }
        return currentContact
    }

    override fun showDatePicker() {
        DatePickerFragment({
            dateSelected ->
            birthdayInput.inputText(dateSelected)
        }).show(supportFragmentManager, "datepicker")
    }
}
