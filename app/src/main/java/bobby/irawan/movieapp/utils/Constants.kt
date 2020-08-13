package bobby.irawan.movieapp.utils

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
object Constants {
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_INTERCEPTOR = "HeaderInterceptor"
    const val LOGGING_INTERCEPTOR = "LoggingInterceptor"
    const val QUERY_PARAM_API_KEY = "api_key"
    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val CONTENT_TYPE_VALUE = "application/json;charset=utf-8"
    const val PATH_MOVIE = "movie/"
    const val PATH_POPULAR = "popular"
    const val PATH_NOW_PLAYING = "now_playing"
    const val PATH_TOP_RATED = "top_rated"
    const val PATH_REVIEW = "reviews"
    const val PATH_MOVIE_ID = "movie_id"

    sealed class Result {
        data class Success<T>(val data: T?) : Result()
        data class Error(val message: String?) : Result()
    }

}