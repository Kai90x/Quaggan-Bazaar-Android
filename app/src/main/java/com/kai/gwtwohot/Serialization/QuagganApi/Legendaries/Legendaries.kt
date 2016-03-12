package com.kai.gwtwohot.Serialization.QuagganApi.Legendaries

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Legendaries {
    var name: String? = null
    var id: String? = null
    var date_created: String? = null
    var icon: String? = null
    var rarity: String? = null
    var type: String? = null
}
