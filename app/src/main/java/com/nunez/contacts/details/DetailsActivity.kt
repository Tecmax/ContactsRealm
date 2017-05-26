package com.nunez.contacts.details

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.nunez.contacts.R
import com.nunez.contacts.editContact.EditContactActivity
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.list.ContactsListActivity
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.details_activity.*

class DetailsActivity : AppCompatActivity(), DetailsContactContract.View {


    companion object{
        const val EXTRA_CONTACT_ID = "contact_id"
    }

    var currentContact = Contact()
    val interactor: DetailsContactContract.Interactor by lazy { DetailsInteractor(ContactsRepository()) }
    val presenter: DetailsContactContract.Presenter by lazy { DetailsPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        supportActionBar?.title = getString(R.string.details_activity_title)

        val arguments = intent.extras
        arguments?.let {
            val id = it.getString(ContactsListActivity.EXTRA_CONTACT_ID)
            presenter.getContactDetails(id)
        }
    }

    override fun onRestart() {
        super.onRestart()
        presenter.getContactDetails(currentContact.id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        when(id){
            R.id.action_edit -> {presenter.onEditClicked()}
            R.id.action_delete -> {presenter.onDeleteClicked(currentContact.id)}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showContactDetails(contact: Contact) {
        currentContact = contact
        firstName.text= contact.firstName
        lastName.text = contact.lastName
        phoneNumber.text = contact.phoneNumber
        zipcodeInput.text = contact.zipCode
        birthday.text = contact.birthday
    }

    override fun goToEditActivity() {
        intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra(EditContactActivity.EXTRA_CONTACT_ID, currentContact.id)
        startActivity(intent)
    }

    override fun close() {
        finish()
    }
}


