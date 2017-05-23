package com.nunez.contacts.common

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Useful extensions
 */
fun ViewGroup.inflate(resource: Int): View {
    return LayoutInflater.from(this.context).inflate(resource, this, false)
}

fun showSnackbar(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, duration).show()
}