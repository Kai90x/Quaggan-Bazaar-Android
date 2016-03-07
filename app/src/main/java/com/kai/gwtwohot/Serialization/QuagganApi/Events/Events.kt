package com.kai.gwtwohot.Serialization.QuagganApi.Events

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 3/7/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Events {
    var area: String? = null
    var boss: String? = null
    var zone: String? = null
    var spawn_time_utc: String? = null
}
