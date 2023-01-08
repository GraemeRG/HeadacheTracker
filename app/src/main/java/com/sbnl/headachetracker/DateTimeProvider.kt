package com.sbnl.headachetracker

import org.joda.time.DateTime

class DateTimeProvider {

    fun nowUtcInMillis(): Long =
        DateTime.now().millis
}