package com.kai.gwtwohot.Factory

import com.fasterxml.jackson.core.JsonFactory
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * Created by ikraammoothianpillay1 on 2/20/16.
 */
object RetrofitFactory {
    internal fun <T> createService(clazz: Class<T>, endPoint: String): T {
        val restAdapter = Retrofit.Builder().baseUrl(endPoint).addConverterFactory(JacksonConverterFactory.create()).build()
        return restAdapter.create(clazz)
    }
}
