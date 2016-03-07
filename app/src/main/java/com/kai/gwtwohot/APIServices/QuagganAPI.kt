package com.kai.gwtwohot.APIServices

import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Serialization.QuagganApi.News.News
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
interface QuagganAPI {

    @GET("/news")
    fun news(@Query("page") page: Int,@Query("batch_size") batch_size: Int = 50): Observable<QuagganJson<News>>
    @GET("/events")
    fun events(): Observable<Details<Events>>

    companion object {
       const val BaseURL = "http://quagganbazaarapi.kai-mx.net"
    }
}
