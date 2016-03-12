package com.kai.gwtwohot.APIServices

import com.kai.gwtwohot.Serialization.QuagganApi.Details
import com.kai.gwtwohot.Serialization.QuagganApi.Dungeons.Dungeons
import com.kai.gwtwohot.Serialization.QuagganApi.Events.Events
import com.kai.gwtwohot.Serialization.QuagganApi.Items.Item
import com.kai.gwtwohot.Serialization.QuagganApi.Legendaries.Legendaries
import com.kai.gwtwohot.Serialization.QuagganApi.News.News
import com.kai.gwtwohot.Serialization.QuagganApi.QuagganJson
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Data
import com.kai.gwtwohot.Serialization.QuagganApi.Simple.Simple
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
interface QuagganAPI {

    @GET("/news")
    fun news(@Query("page") page: Int,
             @Query("batch_size") batch_size: Int = 50): Observable<QuagganJson<News>>

    @GET("/events")
    fun events(): Observable<Details<Events>>

    @GET("/legendaries")
    fun legendaries(): Observable<Details<Legendaries>>

    @GET("/dungeons")
    fun dungeons(): Observable<Details<Dungeons>>

    @POST("/email")
    fun email(@Query("to") to: String,
              @Query("body") body: String,
              @Query("from") from: String,
              @Query("subject") subject: String,
              @Query("items") items: String? = null,
              @Query("notificationid") notificationid: String? = null): Observable<Data<Simple>>

    @GET("/items")
    fun items(@Query("name") name: String? = null,
              @Query("type") type: String? = null,
              @Query("subtype") subtype: String? = null,
              @Query("buyPriceMin") buyPriceMin: Int? = null,
              @Query("buyPriceMax") buyPriceMax: Int? = null,
              @Query("sellPriceMin") sellPriceMin: Int? = null,
              @Query("sellPriceMax") sellPriceMax: Int? = null,
              @Query("rarity") rarity: String? = null,
              @Query("levelmin") levelmin: Int? = null,
              @Query("levelmax") levelmax: Int? = null,
              @Query("orderby") orderby: String? = null,
              @Query("orderDesc") orderDesc: String? = null,
              @Query("islight") islight: Int? = null,
              @Query("includePrice") includePrice: Int? = null,
              @Query("page") page: Int,
              @Query("batch_size") batch_size: Int = 50): QuagganJson<Item>

    companion object {
       const val BaseURL = "http://quagganbazaarapi.kai-mx.net"
    }
}
