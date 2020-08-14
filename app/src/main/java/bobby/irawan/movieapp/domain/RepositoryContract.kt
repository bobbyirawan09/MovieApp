package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.utils.Constants.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
interface RepositoryContract {
    suspend fun getNowPlayingMovies(): Result
    suspend fun getPopularMovies(): Result
    suspend fun getMovieReview(movieId: String): Result
    suspend fun getTopRatedMovies(): Result
    suspend fun getFavorites(): Flow<Result>
    suspend fun getFavoriteByMovieId(movieId: Int): Result
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int
}