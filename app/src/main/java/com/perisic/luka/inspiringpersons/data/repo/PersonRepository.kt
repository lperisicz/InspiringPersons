package com.perisic.luka.inspiringpersons.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson

interface PersonRepository {

    fun fetchPersons(): LiveData<PagedList<InspiringPerson>>

    fun fetchPerson(personId: Int): LiveData<InspiringPerson>

    fun createPerson(person: InspiringPerson)

    fun editPerson(person: InspiringPerson)

    fun deletePerson(personId: Int)

}