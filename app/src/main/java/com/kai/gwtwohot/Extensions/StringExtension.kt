package com.kai.gwtwohot.Extensions

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
fun String.shorten(limit: Int = 12) : String {
    return if (count() > limit) this.substring(0..limit).plus("...") else this
}

fun String.emailValidate() : Boolean {
    val pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    return pattern.matcher(this).matches()
}