package bobby.irawan.movieapp.data.favorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME
import bobby.irawan.movieapp.presentation.model.MovieItem

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

@Entity(tableName = DATABASE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_MOVIE_ID)
    var movieId: Int = 0,
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String = "",
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    var releaseDate: String = "",
    @ColumnInfo(name = COLUMN_POSTER_URL)
    var posterUrl: String = "",
    @ColumnInfo(name = COLUMN_BACKDROP_URL)
    var backdropUrl: String = "",
    @ColumnInfo(name = COLUMN_OVERVIEW)
    var overview: String = "",
    @ColumnInfo(name = COLUMN_VOTE_COUNT)
    var voteCount: Int = 0,
    @ColumnInfo(name = COLUMN_VOTE_AVERAGE)
    var voteAverage: Double = 0.0
) {
    companion object {
        const val DATABASE_TABLE_NAME = "favorite"
        const val COLUMN_MOVIE_ID = "movie_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_POSTER_URL = "poster_url"
        const val COLUMN_BACKDROP_URL = "backdrop_url"
        const val COLUMN_OVERVIEW = "overview"
        const val COLUMN_VOTE_COUNT = "vote_count"
        const val COLUMN_VOTE_AVERAGE = "vote_average"

        fun from(movieItem: MovieItem) = FavoriteEntity(
            movieItem.id,
            movieItem.title,
            movieItem.releaseDate,
            movieItem.posterUrl,
            movieItem.backdropUrl,
            movieItem.overview,
            movieItem.voteCount,
            movieItem.voteAverage
        )
    }
}