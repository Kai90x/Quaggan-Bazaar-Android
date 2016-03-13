package com.kai.gwtwohot.Serialization.QuagganApi.Items

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/13/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Buy {
    var price : String? = null
    var quantity : String? = null
}