package com.nunez.contacts.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Contact(
        @PrimaryKey var id: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var phoneNumber: String = "",
        var birthday: String = "",
        var zipCode: String = ""
) : RealmObject()