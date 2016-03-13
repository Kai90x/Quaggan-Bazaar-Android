package com.kai.gwtwohot.Serialization.QuagganApi.Items

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Item {
    var id: String? = null
    var name: String? = null
    var icon: String? = null
    var description: String? = null
    var rarity: String? = null
    var type: String? = null
    var level: String? = null
    var vendor_value: String? = null
    var default_skin: String? = null
    var flags: List<String>? = null
    var game_types: List<String>? = null
    var restrictions: List<Any>? = null
    var details: Details? = null
    var price: Price? = null
}
