package com.task.movies.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun getDateByMonthName(dateVale: String): String {
        var formatter = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = formatter.parse(dateVale)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        formatter = SimpleDateFormat("MMMM dd, yyyy")

        return formatter.format(date)
    }
}