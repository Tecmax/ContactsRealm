package com.nunez.contacts.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

data class Contact (
    @PrimaryKey var id: Int,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String,
    var birthday: String,
    var zipCode: String
): RealmObject()