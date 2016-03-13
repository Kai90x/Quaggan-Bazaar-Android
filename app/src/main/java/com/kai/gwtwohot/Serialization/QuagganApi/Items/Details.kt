package com.kai.gwtwohot.Serialization.QuagganApi.Items

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/12/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Details {
    var type: String? = null
    var weight_class: String? = null
    var defense: String? = null
    var suffix_item_id: String? = null
    var secondary_suffix_item_id: String? = null
    var size: Any? = null
    var no_sell_or_sort: Any? = null
    var description: Any? = null
    var duration_ms: Any? = null
    var unlock_type: String? = null
    var color_id: Any? = null
    var recipe_id: String? = null
    var charges: String? = null
    var flags: Any? = null
    var infusion_upgrade_flags: Any? = null
    var suffix: Any? = null
    var bonuses: Any? = null
    var damage_type: String? = null
    var min_power: String? = null
    var max_power: String? = null
    var infix_upgrade: Any? = null
}