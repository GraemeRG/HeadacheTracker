package com.sbnl.headachetracker.database.headache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sbnl.headachetracker.repositories.PainLevelOverHeadache
import com.sbnl.headachetracker.repositories.RecordedMedication

@Entity(tableName = "headaches")
data class HeadacheObject(
    @PrimaryKey val dateRecorded: Long, // TimeInMillisWhenRecorded
    val timeNoticed: String,
    val timeCleared: Long? = null,
    val medicationTaken: List<RecordedMedication> = emptyList(),
    val monitoredPainLevel: List<PainLevelOverHeadache> = emptyList()
)

enum class HeadacheStartPeriod(val headachePeriod: String) {
    WOKE_UP("Woke Up"),
    DURING_DAY("During Day"),
    DURING_EVENING("During Evening");

    companion object {
        fun forStartPeriod(startPeriod: String) =
            values().find { it.headachePeriod == startPeriod } ?: WOKE_UP
    }
}