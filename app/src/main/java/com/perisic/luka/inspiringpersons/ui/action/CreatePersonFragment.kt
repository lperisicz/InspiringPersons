package com.perisic.luka.inspiringpersons.ui.action

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.perisic.luka.inspiringpersons.R
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson
import com.perisic.luka.inspiringpersons.util.BaseFragment
import com.perisic.luka.inspiringpersons.util.buildDatePicker
import com.perisic.luka.inspiringpersons.util.formatTimeLong
import kotlinx.android.synthetic.main.fragment_create_person.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreatePersonFragment : BaseFragment(
    R.layout.fragment_create_person
) {

    private val args by navArgs<CreatePersonFragmentArgs>()
    private val viewModel by viewModel<CreatePersonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (args.personId != 0) {
            viewModel.fetchPerson(args.personId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeData()
    }

    private fun setupUi() {
        fabSave.setOnClickListener {
            val data = InspiringPerson(
                name = editTextName.text?.toString() ?: "",
                description = editTextDescription.text?.toString() ?: "",
                image = editTextUrl.text?.toString() ?: "",
                dateOfBirth = viewModel.date.value ?: 0,
                quotes = editTextQuotes.text?.toString()?.split(".") ?: listOf("NESTO.", "DRUGO.")
            )
            if (args.personId == 0) {
                viewModel.createPerson(data)
            } else {
                data.id = args.personId
                viewModel.updatePerson(data)
            }
            findNavController().navigateUp()
        }
        editTextUrl.doOnTextChanged { text, _, _, _ ->
            viewModel.imageUrl.value = text?.toString()
        }
        editTextDate.setOnClickListener {
            buildDatePicker(
                selection = viewModel.date.value ?: System.currentTimeMillis()
            ) { time ->
                viewModel.date.value = time
            }.show(childFragmentManager, "dialogTaskStart")
        }
    }

    private fun observeData() {
        viewModel.personResponse.observe(viewLifecycleOwner, Observer {
            it?.let {
                editTextName.setText(it.name)
                editTextDescription.setText(it.description)
                editTextUrl.setText(it.image)
                editTextQuotes.setText(it.quotes.joinToString(separator = ". "))
                viewModel.date.value = it.dateOfBirth
                viewModel.imageUrl.value = it.image
            }
        })
        viewModel.date.observe(viewLifecycleOwner, Observer {
            editTextDate.setText(formatTimeLong(it))
        })
        viewModel.imageUrl.observe(viewLifecycleOwner, Observer {
            Glide.with(imageViewProfile)
                .load(it ?: R.drawable.ic_launcher_background)
                .into(imageViewProfile)
        })
    }

}