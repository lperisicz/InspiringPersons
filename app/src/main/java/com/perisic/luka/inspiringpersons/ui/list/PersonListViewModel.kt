package com.perisic.luka.inspiringpersons.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.perisic.luka.inspiringpersons.data.repo.PersonRepository

class PersonListViewModel(
    private val personRepository: PersonRepository
) : ViewModel() {
    private val filterData = MutableLiveData(null)

    val personsResponse = switchMap(filterData) {
        personRepository.fetchPersons()
    }

    fun deletePerson(personId: Int) {
        personRepository.deletePerson(personId)
    }

}