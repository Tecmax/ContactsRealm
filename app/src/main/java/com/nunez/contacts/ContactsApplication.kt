package com.nunez.contacts

import android.app.Application
import io.realm.Realm


class ContactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}