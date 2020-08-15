package bobby.irawan.movieapp.data

import bobby.irawan.movieapp.utils.Constants.Result
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 14/08/20.
 */

suspend fun <T> callApi(action: suspend () -> Response<T>): Result =
    try {
        val response = action.invoke()
        Result.Success(data = response.body())
    } catch (e: Exception) {
        Result.Error(message = e.localizedMessage)
    }