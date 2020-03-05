package com.chetan.movietime.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList


/**
 * Created by Chetan on 2020-03-05.
 */
class MyTypeConverter {
    @TypeConverter
    fun stringToList(data: String?): List<Int> {
        val gson = Gson()
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(list: List<Int>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}