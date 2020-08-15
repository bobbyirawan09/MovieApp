package bobby.irawan.movieapp.presentation.model

import android.os.Parcelable
import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.domain.asBackdropUrl
import bobby.irawan.movieapp.domain.asPosterUrl
import bobby.irawan.movieapp.utils.asShowDate
import bobby.irawan.movieapp.utils.orZero
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
@Parcelize
data class MovieItem(
    var id: Int,
    var title: String,
    var posterUrl: String,
    var backdropUrl: String,
    var releaseDate: String,
    var overview: String,
    var voteAverage: Double,
    var voteCount: Int
) : Parcelable {
    companion object {
        fun fromResponse(movieResponse: MovieResponse): List<MovieItem>? {
            return movieResponse.results?.map {
                MovieItem(
                    it.id.orZero(),
                    it.title.orEmpty(),
                    it.posterPath.orEmpty().asPosterUrl(),
                    it.backdropPath.orEmpty().asBackdropUrl(),
                    it.releaseDate.orEmpty().asShowDate(),
                    it.overview.orEmpty(),
                    it.voteAverage.orZero(),
                    it.voteCount.orZero()
                )
            }
        }

        fun fromFavorite(favorite: Favorite) = MovieItem(
            favorite.movieId,
            favorite.title,
            favorite.posterUrl,
            favorite.backdropUrl,
            favorite.releaseDate,
            favorite.overview,
            favorite.voteAverage,
            favorite.voteCount
        )
    }
}