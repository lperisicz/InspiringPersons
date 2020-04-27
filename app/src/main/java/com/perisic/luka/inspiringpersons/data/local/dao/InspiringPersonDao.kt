package com.perisic.luka.inspiringpersons.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.perisic.luka.inspiringpersons.data.local.LocalDatabase
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson

@Dao
interface InspiringPersonDao {

    @Query("SELECT * FROM inspiring_persons")
    fun fetchPersons(): DataSource.Factory<Int, InspiringPerson>

    @Query("SELECT * FROM inspiring_persons WHERE id LIKE :id LIMIT 1")
    fun fetchSingle(id: Int): LiveData<InspiringPerson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: InspiringPerson)

    @Query("DELETE FROM inspiring_persons WHERE id LIKE :id")
    fun delete(id: Int)

    companion object {
        fun create(localDatabase: LocalDatabase): InspiringPersonDao =
            localDatabase.inspiringPersonDao()
    }

}