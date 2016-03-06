package com.kai.gwtwohot.Utils

import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by ikraammoothianpillay1 on 3/6/16.
 */
object ExceptionUtils {
    fun getStackTrace(throwable: Throwable): String {
        val sw = StringWriter()
        val pw = PrintWriter(sw, true)
        throwable.printStackTrace(pw)
        return sw.buffer.toString()
    }
}