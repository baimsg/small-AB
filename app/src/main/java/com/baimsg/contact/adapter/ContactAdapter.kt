package com.baimsg.contact.adapter

import android.util.Base64
import com.baimsg.contact.databinding.ContactsItemBinding
import com.baimsg.contact.extensions.loadImage
import com.baimsg.data.entities.Contacts

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
class ContactAdapter : BaseBindingAdapter<ContactsItemBinding, Contacts>() {
    override fun convert(holder: VBViewHolder<ContactsItemBinding>, item: Contacts) {
        with(holder.vb) {
            ivHeader.loadImage(Base64.decode(item.header ?: "", Base64.URL_SAFE), round = 120f)
            tvName.text = item.name
        }
    }
}