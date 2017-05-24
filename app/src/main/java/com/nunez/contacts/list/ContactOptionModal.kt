package com.nunez.contacts.list

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nunez.contacts.R
import kotlinx.android.synthetic.main.contact_option_modal.*

class ContactOptionModal(
        val contactId: String,
        val editClicked: (String) -> Unit,
        val deleteClicked: (String) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.contact_option_modal, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editContact.setOnClickListener { editClicked(contactId) }
        deleteContact.setOnClickListener { deleteClicked(contactId) }
    }
}