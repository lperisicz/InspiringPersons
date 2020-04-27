package com.perisic.luka.inspiringpersons.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson
import com.perisic.luka.inspiringpersons.databinding.ItemPersonBinding
import com.perisic.luka.inspiringpersons.util.BasePagedRecyclerAdapter
import com.perisic.luka.inspiringpersons.util.formatTimeLong
import kotlin.random.Random

class PersonListAdapter : BasePagedRecyclerAdapter<InspiringPerson, ItemPersonBinding>(
    inflater = ItemPersonBinding::inflate,
    diffCallback = object : DiffUtil.ItemCallback<InspiringPerson>() {

        override fun areItemsTheSame(oldItem: InspiringPerson, newItem: InspiringPerson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: InspiringPerson,
            newItem: InspiringPerson
        ): Boolean {
            return oldItem.id == newItem.id
        }
    },
    binder = {
        person = it
        textViewBirth.text = formatTimeLong(it.dateOfBirth)
        Glide.with(imageViewProfile)
            .load(it.image)
            .into(imageViewProfile)
        root.setOnClickListener { _ ->
            Snackbar.make(root, it.quotes[Random.nextInt(0, it.quotes.size)], Snackbar.LENGTH_SHORT)
                .show()
        }
    }
)