package com.perisic.luka.inspiringpersons.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.perisic.luka.inspiringpersons.data.local.dao.InspiringPersonDao
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson

internal class PersonRepositoryImpl(
    private val personDao: InspiringPersonDao
) : PersonRepository {

    override fun fetchPersons(): LiveData<PagedList<InspiringPerson>> {
        return personDao.fetchPersons().toLiveData(
            pageSize = 20
        )
    }

    override fun editPerson(person: InspiringPerson) {
        personDao.insert(person)
    }

    override fun deletePerson(personId: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchPerson(personId: Int): LiveData<InspiringPerson> {
        TODO("Not yet implemented")
    }

    override fun createPerson(person: InspiringPerson) {
        personDao.insert(person)
    }

}