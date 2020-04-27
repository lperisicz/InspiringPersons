package com.perisic.luka.inspiringpersons.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inspiring_persons")
data class InspiringPerson(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val name: String,
    val image: String,
    val dateOfBirth: Long,
    val description: String,
    val quotes: List<String>
)