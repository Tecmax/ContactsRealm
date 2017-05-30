package com.nunez.contacts.dependencyInjection.contactlist

import com.nunez.contacts.list.ContactListContract
import com.nunez.contacts.list.ContactListInteractor
import com.nunez.contacts.list.ContactsListPresenter
import com.nunez.contacts.repository.ContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class ListModule(val view: ContactListContract.View){

    @Provides
    @Singleton
    fun provideRepository() = ContactsRepository()

    @Provides
    @Singleton
    fun providesInteractor(): ContactListContract.Interactor = ContactListInteractor(provideRepository())

    @Provides
    @Singleton
    fun providesPresenter() = ContactsListPresenter(view, providesInteractor())
}