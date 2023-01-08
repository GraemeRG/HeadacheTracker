package com.sbnl.headachetracker

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class DateTimeProvider {

    fun nowUtcInMillis(): Long =
        DateTime
            .now(DateTimeZone.UTC)
            .millis

    fun startOfCurrentDayInMillis(): Long =
        DateTime
            .now()
            .withTime(0, 0, 0, 0)
            .withZone(DateTimeZone.UTC)
            .millis
}