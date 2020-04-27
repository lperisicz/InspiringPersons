package com.perisic.luka.inspiringpersons.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.perisic.luka.inspiringpersons.R
import com.perisic.luka.inspiringpersons.util.BaseFragment
import com.perisic.luka.inspiringpersons.util.startSupportActionMode
import kotlinx.android.synthetic.main.fragment_person_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class PersonListFragment : BaseFragment(
    R.layout.fragment_person_list
) {

    private val viewModel by viewModel<PersonListViewModel>()
    private val adapter = PersonListAdapter {
        startSupportActionMode(R.menu.menu_edit_delete) { mode, item ->
            mode?.finish()
            when (item?.itemId) {
                R.id.action_edit -> {
                    findNavController().navigate(
                        PersonListFragmentDirections.actionPersonListFragmentToCreatePersonFragment(
                            personId = it
                        )
                    )
                    true
                }
                R.id.action_delete -> {
                    viewModel.deletePerson(it)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeData()
    }

    private fun setupUi() {
        recyclerViewPersons.adapter = adapter
        fabAddPerson.setOnClickListener {
            findNavController().navigate(
                PersonListFragmentDirections.actionPersonListFragmentToCreatePersonFragment()
            )
        }
    }

    private fun observeData() {
        viewModel.personsResponse.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}