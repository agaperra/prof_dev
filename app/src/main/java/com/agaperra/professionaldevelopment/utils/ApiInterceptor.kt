package com.agaperra.professionaldevelopment.utils


import com.agaperra.professionaldevelopment.BuildConfig
import okhttp3.Interceptor

object ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder().url(
            chain.request().url.newBuilder().addQueryParameter("key", BuildConfig.key).build()
        ).build()
    )
}