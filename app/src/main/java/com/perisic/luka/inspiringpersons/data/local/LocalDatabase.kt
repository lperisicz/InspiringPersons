package com.perisic.luka.inspiringpersons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.perisic.luka.inspiringpersons.data.local.dao.InspiringPersonDao
import com.perisic.luka.inspiringpersons.data.local.model.InspiringPerson

@Database(
    entities = [
        InspiringPerson::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun inspiringPersonDao(): InspiringPersonDao

}