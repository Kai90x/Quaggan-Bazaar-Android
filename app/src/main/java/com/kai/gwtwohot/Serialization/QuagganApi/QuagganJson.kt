package com.kai.gwtwohot.Serialization.QuagganApi

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class QuagganJson<T>
{
    var data: Data<T>? = null;
}
