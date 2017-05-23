package com.nunez.contacts.list

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.nunez.contacts.R
import com.nunez.contacts.common.ContactsAdapter
import com.nunez.contacts.common.showSnackbar
import com.nunez.contacts.entities.Contact
import com.nunez.contacts.repository.ContactsRepository
import kotlinx.android.synthetic.main.contacts_list_activity.*
import kotlinx.android.synthetic.main.content_contatcs_list.*

class ContactsListActivity : AppCompatActivity(), ContactListContract.View {
    lateinit var adapter: ContactsAdapter
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
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
        }
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
                {contactClickedId ->
                    print(contactClickedId)
                },
                {contactLongClickedId ->
                    print(contactLongClickedId)
                }
        )

        contactsRecycler.adapter = adapter

    }

    override fun showContactDetails(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage() {
        showSnackbar(contactsRecycler, getString(R.string.message_error), Snackbar.LENGTH_SHORT)
    }

    override fun showDeleteBottomSheet(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToActivity(intent: Intent) {
        startActivity(intent)
    }

}
