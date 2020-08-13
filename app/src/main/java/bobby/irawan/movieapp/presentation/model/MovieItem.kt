package bobby.irawan.movieapp.presentation.model

import android.os.Parcelable
import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.utils.orZero
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
@Parcelize
data class MovieItem(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable {
    companion object {
        fun from(movieResponse: MovieResponse): List<MovieItem>? {
            return movieResponse.results?.map {
                MovieItem(
                    it.id.orZero(),
                    it.title.orEmpty(),
                    it.posterPath.orEmpty(),
                    it.backdropPath.orEmpty(),
                    it.releaseDate.orEmpty(),
                    it.overview.orEmpty(),
                    it.voteAverage.orZero(),
                    it.voteCount.orZero()
                )
            }
        }
    }
}