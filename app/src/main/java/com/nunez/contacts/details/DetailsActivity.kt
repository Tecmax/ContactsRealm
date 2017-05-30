package com.nunez.contacts.details

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.widget.TextView
import com.nunez.contacts.R
import com.nunez.contacts.editContact.EditContactActivity
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.list.ContactsListActivity
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.details_activity.*

class DetailsActivity : AppCompatActivity(), DetailsContactContract.View {


    companion object {
        const val EXTRA_CONTACT_ID = "contact_id"
    }

    var currentContact = Contact()
    val interactor: DetailsContactContract.Interactor by lazy { DetailsInteractor(ContactsRepository()) }
    val presenter: DetailsContactContract.Presenter by lazy { DetailsPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        supportActionBar?.let {
            with(it) {
                title = getString(R.string.details_activity_title)
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

        when (id) {
            R.id.action_edit -> presenter.onEditClicked()
            R.id.action_delete -> presenter.onDeleteClicked(currentContact.id)
            android.R.id.home -> close()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showContactDetails(contact: Contact) {
        currentContact = contact
        handleEmptyValues(firstName, contact.firstName,null)
        handleEmptyValues(lastName, contact.lastName,null)
        handleEmptyValues(phoneNumber, contact.phoneNumber, phoneImage)
        handleEmptyValues(zipcodeInput, contact.zipCode, zipImage)
        handleEmptyValues(birthday, contact.birthday, birthdayImage)
    }

    override fun goToEditActivity() {
        intent = Intent(this, EditContactActivity::class.java)
        intent.putExtra(EditContactActivity.EXTRA_CONTACT_ID, currentContact.id)
        startActivity(intent)
    }

    override fun close() {
        finish()
    }

    private fun handleEmptyValues(textView: TextView, value: String, image: View?){
        if(value.isEmpty()){
            textView.visibility = GONE
            image?.visibility = GONE
        }
        else
            textView.text = value
    }
}


