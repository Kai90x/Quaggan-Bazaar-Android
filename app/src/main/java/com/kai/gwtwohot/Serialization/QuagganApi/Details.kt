package com.kai.gwtwohot.Serialization.QuagganApi

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import rx.Observable


/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Details<T> {
    var data: List<T>? = null
}
