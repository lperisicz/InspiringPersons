package com.perisic.luka.inspiringpersons.ui.action

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson
import com.perisic.luka.inspiringpersons.data.repo.PersonRepository

class CreatePersonViewModel(
    private val personRepository: PersonRepository
) : ViewModel() {

    private val personId = MutableLiveData<Int>()
    val personResponse = switchMap(personId, personRepository::fetchPerson)
    val date = MutableLiveData<Long>()
    val imageUrl = MutableLiveData<String>()

    fun fetchPerson(personId: Int) {
        this.personId.value = personId
    }

    fun createPerson(person: InspiringPerson) {
        personRepository.createPerson(person)
    }

    fun updatePerson(person: InspiringPerson) {
        personRepository.editPerson(person)
    }

}