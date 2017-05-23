package com.nunez.contacts.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Useful extensions
 */
fun ViewGroup.inflate(resource: Int): View {
    return LayoutInflater.from(this.context).inflate(resource, this, false)
}