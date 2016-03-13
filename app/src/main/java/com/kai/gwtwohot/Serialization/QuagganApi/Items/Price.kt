package com.kai.gwtwohot.Serialization.QuagganApi.Items

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Price {
    var buy : Buy? = null
    var sell : Sell? = null
    var date_modified : String? = null
}