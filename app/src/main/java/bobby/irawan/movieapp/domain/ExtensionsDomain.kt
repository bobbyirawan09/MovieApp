package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.utils.Constants
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
            Success(converter(this.data))
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

suspend fun <T> handleFlowData(action: suspend () -> Flow<T>) = flow {
    try {
        action.invoke().collect { result ->
            emit(Success(result))
        }
    } catch (e: Exception) {
        emit(Error(e.localizedMessage))
    }
}