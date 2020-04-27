package com.perisic.luka.inspiringpersons.data

import androidx.room.Room
import com.perisic.luka.inspiringpersons.data.local.LocalDatabase
import com.perisic.luka.inspiringpersons.data.local.dao.InspiringPersonDao
import com.perisic.luka.inspiringpersons.data.repo.PersonRepository
import com.perisic.luka.inspiringpersons.data.repo.PersonRepositoryImpl
import org.koin.dsl.module

val localModule = module {
    single<LocalDatabase> {
        Room.databaseBuilder(
            get(),
            LocalDatabase::class.java,
            "inspiringPersonsDb"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        InspiringPersonDao.create(get())
    }
}

val repoModule = module {
    single<PersonRepository> {
        PersonRepositoryImpl(get())
    }
}