package com.nunez.contacts.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.nunez.contacts.R
import com.nunez.contacts.addContact.AddContactActivity
import com.nunez.contacts.common.ContactsAdapter
import com.nunez.contacts.details.DetailsActivity
import com.nunez.contacts.editContact.EditContactActivity
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.contacts_list_activity.*
import kotlinx.android.synthetic.main.content_contatcs_list.*

class ContactsListActivity : AppCompatActivity(), ContactListContract.View {

    companion object {
        const val TAG = "ContactsListActivity"
        const val EXTRA_CONTACT_ID = "contact_id"
    }

    lateinit var adapter: ContactsAdapter

    var modal: ContactOptionModal? = null
    val repository = ContactsRepository()
    val interactor by lazy { ContactListInteractor(repository, applicationContext) }
    val presenter by lazy { ContactsListPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contacts_list_activity)
        setSupportActionBar(toolbar)

        presenter.requestContacts()

        contactsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        addContact.setOnClickListener { view ->
            presenter.addContactClicked()
        }
    }

    override fun onResume() {
        super.onResume()
        dismissModal()
        presenter.requestContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.contacts_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showContacts(contacts: List<Contact>) {
        adapter = ContactsAdapter(contacts,
                { id ->
                    presenter.contactCliked(id)
                },
                { id ->
                    presenter.contactLongCliked(id)
                }
        )

        contactsRecycler.adapter = adapter

    }

    override fun showContactDetails(id: String) {
        val intent = Intent(this, DetailsActivity::class.java)

        if (id.isNotEmpty()){
            intent.putExtra(DetailsActivity.EXTRA_CONTACT_ID, id)
            startActivity(intent)
        }
    }

    override fun showErrorMessage() {
    }

    override fun showOptionsModalBottomSheet(id: String) {
        modal = ContactOptionModal(id,
                { presenter.contactToEditClicked(it) },
                { presenter.contactToDeleteClicked(it) }
        )

        modal?.show(supportFragmentManager, "modal")
    }

    override fun goToEditContactActivity(contactId: String) {
        val intent = Intent(this, EditContactActivity::class.java)

        if (contactId.isNotEmpty()){
            intent.putExtra(EXTRA_CONTACT_ID, contactId)
            startActivity(intent)
        }
    }

    override fun goToAddContactActivity() {
        startActivity(Intent(this, AddContactActivity::class.java))
    }

    override fun dismissModal() {
        modal?.dismiss()
    }
}
