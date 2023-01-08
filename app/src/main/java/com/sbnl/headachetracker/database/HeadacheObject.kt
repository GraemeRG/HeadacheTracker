package com.sbnl.headachetracker.database

import androidx.room.*

@Entity
data class HeadacheObject(
    @PrimaryKey val dateRecorded: Long, // TimeInMillisWhenRecorded
    val timeNoticed: String
)

enum class HeadacheStartPeriod(val headachePeriod: String) {
    WOKE_UP("Woke Up"), DURING_DAY("During Day"), DURING_EVENING("During Evening")
}