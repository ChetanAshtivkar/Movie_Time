package com.chetan.movietime.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


/**
 * Created by Chetan on 2020-03-07.
 */
class CommonTypeConverters {

    var gson = Gson()

    @TypeConverter
    fun stringToList(data: String?): List<Int>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(list: List<Int>?): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToGenres(data: String?): List<Genres>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Genres>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun genresListToString(someObjects: List<Genres>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToSpokenLanguages(data: String?): List<SpokenLanguages>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<SpokenLanguages>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun spokenLanguagesListToString(someObjects: List<SpokenLanguages>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToProductionCompanies(data: String?): List<ProductionCompanies>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<ProductionCompanies>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun productionCompaniesListToString(someObjects: List<ProductionCompanies>?): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToProductionCountries(data: String?): List<ProductionCountries>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<ProductionCountries>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun productionCountriesListToString(someObjects: List<ProductionCountries>?): String {
        return gson.toJson(someObjects)
    }
}