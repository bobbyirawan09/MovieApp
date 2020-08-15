package bobby.irawan.movieapp.data.favorite

import androidx.room.*
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity.Companion.COLUMN_MOVIE_ID
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE $COLUMN_MOVIE_ID = :movieId")
    suspend fun getFavoriteByMovieId(movieId: Int): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favouriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int
}