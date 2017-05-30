package com.nunez.contacts.dependencyInjection.contactlist

import com.nunez.contacts.list.ContactsListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ListModule::class))
interface ListComponent {

    fun inject(listActivity: ContactsListActivity)
}