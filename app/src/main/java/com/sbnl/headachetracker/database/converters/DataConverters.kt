package com.sbnl.headachetracker.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sbnl.headachetracker.repositories.RecordedMedication
import java.lang.reflect.Type

class DataConverters {

    @TypeConverter
    fun listOfRecordedMedicationToString(value: List<RecordedMedication>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RecordedMedication>?>() {}.type
        return value
            .let { gson.toJson(it, type) }
    }

    @TypeConverter
    fun stringToRecordedMedication(value: String): List<RecordedMedication> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<RecordedMedication>?>() {}.type
        return gson
            .fromJson<List<RecordedMedication>?>(value, type)
            .map {
                RecordedMedication(id = it.id, timeTaken = it.timeTaken)
            }
    }
}