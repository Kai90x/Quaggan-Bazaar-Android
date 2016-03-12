package com.kai.gwtwohot.Serialization.QuagganApi.Simple

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Data<T> {
    val data: T? = null
}