package com.kai.gwtwohot.Serialization.QuagganApi.Items

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Attribute {
    var attribute: String? = null
    var modifier: String? = null
}