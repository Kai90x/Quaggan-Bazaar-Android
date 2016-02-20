package com.kai.gwtwohot.Serialization.QuagganApi

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
class Data<T> {
    @JsonProperty("Batch")
    var batch: Batch? = null
    var details: Details<T>? = null
}
