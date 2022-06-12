package com.example.aposs_buyer.utils

import kotlin.math.floor
import kotlin.math.sin

class LunarConverter private constructor(){

    // set up singleton
    companion object{
        private var instance: LunarConverter? = null
        fun getInstance(): LunarConverter{
            if(instance==null){
                instance = LunarConverter()
            }
            return instance!!
        }
    }
    private val pi = Math.PI
    /**
     *
     * @param dd
     * @param mm
     * @param yy
     * @return the number of days since 1 January 4713 BC (Julian calendar)
     */
    private fun jdFromDate(dd: Int, mm: Int, yy: Int): Int {
        val a = (14 - mm) / 12
        val y = yy + 4800 - a
        val m = mm + 12 * a - 3
        var jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045
        if (jd < 2299161) {
            jd = dd + (153 * m + 2) / 5 + 365 * y + y / 4 - 32083
        }
        //jd = jd - 1721425;
        return jd
    }

    /**
     * Solar longitude in degrees
     * Algorithm from: Astronomical Algorithms, by Jean Meeus, 1998
     * @param jdn - number of days since noon UTC on 1 January 4713 BC
     * @return
     */
    private fun sunLongitude(jdn: Double): Double {
        //return CC2K.sunLongitude(jdn);
        return sunLongitudeAA98(jdn)
    }

    private fun sunLongitudeAA98(jdn: Double): Double {
        val t = (jdn - 2451545.0) / 36525 // Time in Julian centuries from 2000-01-01 12:00:00 GMT
        val t2 = t * t
        val dr = pi / 180 // degree to radian
        val m =
            357.52910 + 35999.05030 * t - 0.0001559 * t2 - 0.00000048 * t * t2 // mean anomaly, degree
        val l0 = 280.46645 + 36000.76983 * t + 0.0003032 * t2 // mean longitude, degree
        var dl = (1.914600 - 0.004817 * t - 0.000014 * t2) * sin(dr * m)
        dl += (0.019993 - 0.000101 * t) * sin(dr * 2 * m) + 0.000290 * sin(dr * 3 * m)
        var l = l0 + dl // true longitude, degree
        l -= 360 * toInt(l / 360) // Normalize to (0, 360)
        return l
    }

    private fun newMoon(k: Int): Double {
        //return CC2K.newMoonTime(k);
        return newMoonAA98(k)
    }

    /**
     * Julian day number of the kth new moon after (or before) the New Moon of 1900-01-01 13:51 GMT.
     * Accuracy: 2 minutes
     * Algorithm from: Astronomical Algorithms, by Jean Meeus, 1998
     * @param k
     * @return the Julian date number (number of days since noon UTC on 1 January 4713 BC) of the New Moon
     */
    private fun newMoonAA98(k: Int): Double {
        val t = k / 1236.85 // Time in Julian centuries from 1900 January 0.5
        val t2 = t * t
        val t3 = t2 * t
        val dr = pi / 180
        var jd1 =
            2415020.75933 + 29.53058868 * k + 0.0001178 * t2 - 0.000000155 * t3
        jd1 += 0.00033 * sin((166.56 + 132.87 * t - 0.009173 * t2) * dr) // Mean new moon
        val m =
            359.2242 + 29.10535608 * k - 0.0000333 * t2 - 0.00000347 * t3 // Sun's mean anomaly
        val mpr =
            306.0253 + 385.81691806 * k + 0.0107306 * t2 + 0.00001236 * t3 // Moon's mean anomaly
        val f =
            21.2964 + 390.67050646 * k - 0.0016528 * t2 - 0.00000239 * t3 // Moon's argument of latitude
        var c1 =
            (0.1734 - 0.000393 * t) * sin(m * dr) + 0.0021 * sin(2 * dr * m)
        c1 = c1 - 0.4068 * sin(mpr * dr) + 0.0161 * sin(dr * 2 * mpr)
        c1 -= 0.0004 * sin(dr * 3 * mpr)
        c1 =
            c1 + 0.0104 * sin(dr * 2 * f) - 0.0051 * sin(dr * (m + mpr))
        c1 =
            c1 - 0.0074 * sin(dr * (m - mpr)) + 0.0004 * sin(dr * (2 * f + m))
        c1 =
            c1 - 0.0004 * sin(dr * (2 * f - m)) - 0.0006 * sin(dr * (2 * f + mpr))
        c1 += 0.0010 * sin(dr * (2 * f - mpr)) + 0.0005 * sin(dr * (2 * mpr + m))
        val deltaT: Double = if (t < -11) {
            0.001 + 0.000839 * t + 0.0002261 * t2 - 0.00000845 * t3 - 0.000000081 * t * t3
        } else {
            -0.000278 + 0.000265 * t + 0.000262 * t2
        }
        return jd1 + c1 - deltaT
    }

    private fun toInt(d: Double): Int {
        return floor(d).toInt()
    }
    private fun getSunLongitude(dayNumber: Int, timeZone: Double): Double {
        return sunLongitude(dayNumber - 0.5 - timeZone / 24)
    }
    private fun getNewMoonDay(k: Int, timeZone: Double): Int {
        val jd = newMoon(k)
        return toInt(jd + 0.5 + timeZone / 24)
    }
    private fun getLunarMonth11(yy: Int, timeZone: Double): Int {
        val off = jdFromDate(31, 12, yy) - 2415021.076998695
        val k = toInt(off / 29.530588853)
        var nm = getNewMoonDay(k, timeZone)
        val sunLong = toInt(getSunLongitude(nm, timeZone) / 30)
        if (sunLong >= 9) {
            nm = getNewMoonDay(k - 1, timeZone)
        }
        return nm
    }
    private fun getLeapMonthOffset(a11: Int, timeZone: Double): Int {
        val k = toInt(0.5 + (a11 - 2415021.076998695) / 29.530588853)
        var last: Int // Month 11 contains point of sun longitude 3*PI/2 (December solstice)
        var i = 1 // We start with the month following lunar month 11
        var arc = toInt(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30)
        do {
            last = arc
            i++
            arc = toInt(getSunLongitude(getNewMoonDay(k + i, timeZone), timeZone) / 30)
        } while (arc != last && i < 14)
        return i - 1
    }

    private fun convertSolar2Lunar(dd: Int, mm: Int, yy: Int, timeZone: Double): IntArray {
        val lunarDay: Int
        var lunarMonth: Int
        var lunarYear: Int
        var lunarLeap: Int
        val dayNumber = jdFromDate(dd, mm, yy)
        val k = toInt((dayNumber - 2415021.076998695) / 29.530588853)
        var monthStart = getNewMoonDay(k + 1, timeZone)
        if (monthStart > dayNumber) {
            monthStart = getNewMoonDay(k, timeZone)
        }
        var a11 = getLunarMonth11(yy, timeZone)
        var b11 = a11
        if (a11 >= monthStart) {
            lunarYear = yy
            a11 = getLunarMonth11(yy - 1, timeZone)
        } else {
            lunarYear = yy + 1
            b11 = getLunarMonth11(yy + 1, timeZone)
        }
        lunarDay = dayNumber - monthStart + 1
        val diff = toInt(((monthStart - a11) / 29).toDouble())
        lunarLeap = 0
        lunarMonth = diff + 11
        if (b11 - a11 > 365) {
            val leapMonthDiff = getLeapMonthOffset(a11, timeZone)
            if (diff >= leapMonthDiff) {
                lunarMonth = diff + 10
                if (diff == leapMonthDiff) {
                    lunarLeap = 1
                }
            }
        }
        if (lunarMonth > 12) {
            lunarMonth -= 12
        }
        if (lunarMonth >= 11 && diff < 4) {
            lunarYear -= 1
        }
        return intArrayOf(lunarDay, lunarMonth, lunarYear, lunarLeap)
    }
    fun getLunarYear(dayString: String): Int{
        val temp = dayString.split("/")
        val day =  temp[0].toInt()
        val month = temp[1].toInt()
        val year = temp[2].toInt()
        val tz = 7.0
        return  convertSolar2Lunar(day, month, year, tz)[2]
    }
}