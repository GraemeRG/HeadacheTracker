package com.sbnl.headachetracker.utils.datetime

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateTimeConverter() {
    fun toDateTime(millis: Long): DateTime = DateTime(millis)
}

fun DateTime.toDisplayTime(): String =
    this.toString(displayFormatter)


private const val DISPLAY_FORMAT = "HH:mm"
private val displayFormatter = DateTimeFormat.forPattern(DISPLAY_FORMAT)