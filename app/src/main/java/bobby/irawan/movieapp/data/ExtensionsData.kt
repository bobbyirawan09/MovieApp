package bobby.irawan.movieapp.data

import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.utils.Constants.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 14/08/20.
 */

suspend fun callApi(action: suspend () -> Response<MovieResponse>): Result =
    try {
        val response = action.invoke()
        Result.Success(data = response.body())
    } catch (e: Exception) {
        Result.Error(message = e.localizedMessage)
    }