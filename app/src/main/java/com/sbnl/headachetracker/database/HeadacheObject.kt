package com.sbnl.headachetracker.database

import androidx.room.*

@Entity(tableName = "headaches")
data class HeadacheObject(
    @PrimaryKey val dateRecorded: Long, // TimeInMillisWhenRecorded
    val timeNoticed: String,
    val timeCleared: Long? = null
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