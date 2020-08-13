package bobby.irawan.movieapp.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.Constants.Result
import bobby.irawan.movieapp.utils.Constants.Result.Error
import bobby.irawan.movieapp.utils.Constants.Result.Success
import com.bumptech.glide.Glide
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

fun ImageView.setGlideAttribute(imageUrl: String) =
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_broken)
        .fallback(R.drawable.ic_image_broken)
        .into(this)

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

suspend fun callApi(action: suspend () -> Response<MovieResponse>): Result =
    try {
        val response = action.invoke()
        Success(data = response.body())
    } catch (e: Exception) {
        Error(message = e.localizedMessage)
    }

suspend fun mapToPresentation(action: suspend () -> Result): Result {
    val response = action.invoke()
    return when (response) {
        is Success<*> -> {
            val movieResponse = response.data as MovieResponse
            Success(data = MovieItem.from(movieResponse))
        }
        is Error -> Error(response.message)
    }
}