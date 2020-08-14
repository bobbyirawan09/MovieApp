package bobby.irawan.movieapp.data.favorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME
import bobby.irawan.movieapp.presentation.model.Favorite

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

@Entity(tableName = DATABASE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int = 0,
    @ColumnInfo(name = COLUMN_MOVIE_ID)
    var movieId: Int = 0,
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String = "",
    @ColumnInfo(name = COLUMN_RELEASE_DATE)
    var releaseDate: String = "",
    @ColumnInfo(name = COLUMN_IMAGE_URL)
    var imageUrl: String = "",
    @ColumnInfo(name = COLUMN_OVERVIEW)
    var overview: String = ""
) {
    companion object {
        const val DATABASE_TABLE_NAME = "favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_MOVIE_ID = "movie_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_OVERVIEW = "overview"

        fun from(favorite: Favorite) = FavoriteEntity(
            favorite.id,
            favorite.movieId,
            favorite.title,
            favorite.releaseDate,
            favorite.imageUrl,
            favorite.overview
        )
    }
}