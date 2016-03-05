package com.kai.gwtwohot.Utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/5/16.
 */
object DateUtils {
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