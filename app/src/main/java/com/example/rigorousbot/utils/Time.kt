package com.example.rigorousbot.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date

object Time {

    fun timeStamp(): String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val sdf = SimpleDateFormat ("HH:mm")
        val time = sdf.format(Date(timeStamp.time))

        return time.toString()

    }
}