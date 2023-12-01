package com.example.youtubeclone.utils

import android.os.Build
import androidx.annotation.RequiresApi
import org.ocpsoft.prettytime.PrettyTime
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import java.util.Locale
import java.util.TimeZone


object Utilities {
    /**
     *
     * @param duration
     * @return "01:02:30"
     */
    fun convertYouTubeDuration(duration: String): String {
        val c: Calendar = GregorianCalendar()
        try {
            val df: DateFormat = SimpleDateFormat("'PT'mm'M'ss'S'")
            val d = df.parse(duration)
            c.time = d
        } catch (e: ParseException) {
            try {
                val df: DateFormat = SimpleDateFormat("'PT'hh'H'mm'M'ss'S'")
                val d = df.parse(duration)
                c.time = d
            } catch (e1: ParseException) {
                try {
                    val df: DateFormat = SimpleDateFormat("'PT'ss'S'")
                    val d = df.parse(duration)
                    c.time = d
                } catch (e2: ParseException) {
                }
            }
        }
        c.timeZone = TimeZone.getDefault()
        var time = ""
        if (c[Calendar.HOUR] > 0) {
            if (c[Calendar.HOUR].toString().length == 1) {
                time += "0" + c[Calendar.HOUR]
            } else {
                time += c[Calendar.HOUR]
            }
            time += ":"
        }
        // test minute
        if (c[Calendar.MINUTE].toString().length == 1) {
            time += "0" + c[Calendar.MINUTE]
        } else {
            time += c[Calendar.MINUTE]
        }
        time += ":"
        // test second
        if (c[Calendar.SECOND].toString().length == 1) {
            time += "0" + c[Calendar.SECOND]
        } else {
            time += c[Calendar.SECOND]
        }
        return time
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convert(publishedDay: String): String {
        val formatPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val videoDate = LocalDateTime.parse(publishedDay, formatPattern)
        val currentDate = LocalDateTime.now().withNano(0)
        val differenceInSeconds = ChronoUnit.SECONDS.between(videoDate, currentDate)
        val differenceInDays = ChronoUnit.DAYS.between(videoDate, currentDate)
        val differenceInMonths = ChronoUnit.MONTHS.between(videoDate, currentDate)
        return findDifference(differenceInSeconds, differenceInDays, differenceInMonths)
    }

    fun findDifference(
        differenceInSeconds: Long,
        differenceInDays: Long,
        differenceInMonths: Long
    ): String {
        val hours = differenceInSeconds / 3600
        when (differenceInDays) {
            in 21..31 -> {
                return "3 weeks ego"
            }

            in 14..20 -> {
                return "2 weeks ego"
            }

            in 2..13 -> {
                return "$differenceInDays days ego"
            }

            in 0..1 -> {
                return "$hours hours ego"
            }
        }
        if (differenceInMonths in 0..1) {
            return "$differenceInMonths month ego"
        }
        return "$differenceInMonths months ego"
    }

    fun viewCounts(views: Int): String {
        return when {
            views >= 1000000000 -> {
                val formattedViews = views / 1000000
                "${formattedViews}B views"
            }

            views >= 1000000 -> {
                val formattedViews = views / 10000
                "${formattedViews}M views"
            }

            views >= 1000 -> {
                val formattedViews = views / 10000
                "${formattedViews}K views"
            }

            else -> {
                "$views views"
            }
        }
    }

    fun likeCounts(like: Int): String {
        return when {
            like >= 1000000000 -> {
                val formattedLikes = like / 1000000
                "${formattedLikes}B likes"
            }

            like >= 1000000 -> {
                val formattedLikes = like / 10000
                "${formattedLikes}M views"
            }

            like >= 1000 -> {
                val formattedLikes = like / 10000
                "${formattedLikes}K likes"
            }

            else -> {
                "$like like"

            }
        }
    }

    fun dislikeCounts(dislike: Int): String {
        return when {
            dislike >= 1000000000 -> {
                val formattedDislikes = dislike / 1000000
                "${formattedDislikes}B views"
            }

            dislike >= 1000000 -> {
                val formattedDislikes = dislike / 10000
                "${formattedDislikes}M dislike"
            }

            dislike >= 1000 -> {
                val formattedDislikes = dislike / 10000
                "${formattedDislikes}K dislike "
            }

            else -> {
                "$dislike dislike"

            }
        }
    }

    fun subscribersCount(subs: Int): String {
        return when {
            subs >= 1000000000 -> {
                val formattedDislikes = subs / 1000000
                "${formattedDislikes}B subs"
            }

            subs >= 1000000 -> {
                val formattedDislikes = subs / 10000
                "${formattedDislikes}M subs"
            }

            subs >= 1000 -> {
                val formattedDislikes = subs / 10000
                "${formattedDislikes}K subs "
            }

            else -> {
                "$subs subs"

            }
        }
    }

    fun getAgeDate(date: String): String? {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        var time: Long = 0
        try {
            time = sdf.parse(date)!!.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val prettyTime = PrettyTime(Locale.getDefault())
        return prettyTime.format(Date(time))
    }
}

