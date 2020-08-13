package bobby.irawan.movieapp.data.interceptor

import bobby.irawan.movieapp.BuildConfig.API_KEY
import bobby.irawan.movieapp.BuildConfig.BEARER_TOKEN
import bobby.irawan.movieapp.utils.Constants.CONTENT_TYPE_VALUE
import bobby.irawan.movieapp.utils.Constants.HEADER_AUTHORIZATION
import bobby.irawan.movieapp.utils.Constants.HEADER_CONTENT_TYPE
import bobby.irawan.movieapp.utils.Constants.QUERY_PARAM_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url =
            chain.request().url.newBuilder().addQueryParameter(QUERY_PARAM_API_KEY, API_KEY).build()
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .addHeader(HEADER_AUTHORIZATION, BEARER_TOKEN)
                .addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .build()
        )
    }
}