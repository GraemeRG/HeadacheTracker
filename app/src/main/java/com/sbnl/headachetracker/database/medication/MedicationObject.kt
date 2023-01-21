package com.sbnl.headachetracker.database.medication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medication")
data class MedicationObject(
    val name: String
) {
    @PrimaryKey(autoGenerate = true) var medicationId: Long = 0L
}