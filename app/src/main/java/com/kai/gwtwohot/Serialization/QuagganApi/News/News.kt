package com.kai.gwtwohot.Serialization.QuagganApi.News

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class News {
    var title: String? = null
    var link: String? = null
    var publish_date: String? = null
    var description: String? = null
    var content: String? = null
    var creator: String? = null
}
