package com.kai.gwtwohot.Utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/5/16.
 */
object DateUtils {
    val DATEFORMAT: String = "yyyy-MM-dd HH:mm:ss"

    fun getMinutesOfDay(date: Date) :Int {
        val calendar = GregorianCalendar.getInstance()
        calendar.time = date
        return (calendar.get(Calendar.MINUTE) + (calendar.get(Calendar.HOUR_OF_DAY) * 60 ))
    }

    fun getUTCDate(): Date?
    {
        return stringDateToDate(getUTCdatetimeAsString());
    }

    fun  getUTCdatetimeAsString() : String
    {
        val sdf = SimpleDateFormat(DATEFORMAT);
        sdf.timeZone = TimeZone.getTimeZone("UTC");
        val utcTime = sdf.format(Date());
        return utcTime;
    }

    fun stringDateToDate(StrDate: String): Date?
    {
        var dateToReturn: Date? = null;
        var dateFormat = SimpleDateFormat(DATEFORMAT);

        try
        {
            dateToReturn = dateFormat.parse(StrDate) as Date;
        }
        catch (e: ParseException)
        {
            e.printStackTrace();
        }

        return dateToReturn;
    }

    fun toLocalDate(dateString: String,dateFormat: String = "yyyy-MM-dd HH:mm:ss",
                            timezone: String = "UTC",dateExportFormat: String = "dd/MM/yyyy hh:mmaa") : String {
        val formatter = SimpleDateFormat(dateFormat);
        formatter.timeZone = TimeZone.getTimeZone(timezone);
        var value : Date? = null;

        try {
            value = formatter.parse(dateString);
        } catch (e: ParseException) {
            e.printStackTrace();
        }
        val dateFormatter = SimpleDateFormat(dateExportFormat);
        dateFormatter.timeZone = TimeZone.getDefault();
        val dt = dateFormatter.format(value);

        return dt;
    }
}