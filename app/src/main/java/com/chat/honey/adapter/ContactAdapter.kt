package com.chat.honey.adapter

import androidx.core.net.toUri
import com.chat.data.model.ContactItem
import com.chat.honey.databinding.ContactsItemBinding
import com.chat.honey.extensions.loadImage
import com.chat.honey.util.extensions.dp2px

/**
 * Create by Baimsg on 2023/6/15
 *
 **/
class ContactAdapter : BaseBindingAdapter<ContactsItemBinding, ContactItem>() {
    override fun convert(holder: VBViewHolder<ContactsItemBinding>, item: ContactItem) {
        with(holder.vb) {
            ivHeader.loadImage(item.photoThumbUri.toUri(), round = 120f)
            tvName.text = item.name
        }
    }
}