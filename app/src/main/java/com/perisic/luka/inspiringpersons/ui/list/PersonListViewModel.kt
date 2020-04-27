package com.perisic.luka.inspiringpersons.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.perisic.luka.inspiringpersons.data.repo.PersonRepository

class PersonListViewModel(
    personRepository: PersonRepository
) : ViewModel() {

    private val filterData = MutableLiveData(null)
    val personsResponse = switchMap(filterData) {
        personRepository.fetchPersons()
    }

}