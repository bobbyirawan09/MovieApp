package bobby.irawan.movieapp.presentation.model

import android.os.Parcelable
import bobby.irawan.movieapp.data.movies.model.MovieReviewResultResponse
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 14/08/20.
 */
@Parcelize
data class MovieReview(
    var id: String,
    var author: String,
    var content: String,
    var url: String
) : Parcelable {
    companion object {
        fun from(response: MovieReviewResultResponse) = MovieReview(
            response.id.orEmpty(),
            response.author.orEmpty(),
            response.content.orEmpty(),
            response.url.orEmpty()
        )
    }
}