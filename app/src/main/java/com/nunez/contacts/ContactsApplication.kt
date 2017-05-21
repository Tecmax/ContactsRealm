package com.nunez.contacts

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration




class ContactsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}