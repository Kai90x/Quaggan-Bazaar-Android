package com.kai.gwtwohot.Extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
fun Date.format(dateFormat: String = "yyyy-MM-dd") : String {
    val dateFormatter = SimpleDateFormat(dateFormat);
    val dt = dateFormatter.format(this);
    return dt;
}