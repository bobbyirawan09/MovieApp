package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.BuildConfig.BASE_IMG_URL
import bobby.irawan.movieapp.utils.Constants.DEFAULT_BACKDROP_URL
import bobby.irawan.movieapp.utils.Constants.DEFAULT_POSTER_URL
import bobby.irawan.movieapp.utils.Constants.Result
import bobby.irawan.movieapp.utils.Constants.Result.Error
import bobby.irawan.movieapp.utils.Constants.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by bobbyirawan09 on 14/08/20.
 */

fun <T> Result.mapToPresentation(converter: (Any?) -> T): Result {
    return when (this) {
        is Success<*> -> {
            try {
                Success(converter(this.data))
            } catch (e: java.lang.Exception) {
                Error(e.localizedMessage)
            }
        }
        is Error -> Error(this.message)
    }
}

suspend fun <T> callLocalData(action: suspend () -> T): Result =
    try {
        val result = action.invoke()
        Success(data = result)
    } catch (e: Exception) {
        Error(message = e.localizedMessage)
    }

fun <T> handleFlowData(action: () -> Flow<T>) = flow {
    try {
        action.invoke().collect { result ->
            emit(Success(result))
        }
    } catch (e: Exception) {
        emit(Error(e.localizedMessage))
    }
}

fun String.asPosterUrl() =
    if (this.isEmpty()) DEFAULT_POSTER_URL else BASE_IMG_URL + "w342" + this

fun String.asBackdropUrl() =
    if (this.isEmpty()) DEFAULT_BACKDROP_URL else BASE_IMG_URL + "w780" + this