package com.perisic.luka.inspiringpersons.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringArray(dataList: List<String>): String = Gson().toJson(dataList)

    @TypeConverter
    fun toStringArray(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }

}