package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.data.favorite.FavoriteDao
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.data.movies.MoviesService
import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.data.movies.model.MovieReviewResponse
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.utils.Constants.Result

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class RepositoryImpl(private val service: MoviesService, private val dao: FavoriteDao) :
    RepositoryContract {
    override suspend fun getNowPlayingMovies(): Result {
        return service.getNowPlayingMovies().mapToPresentation { data ->
            MovieItem.fromResponse(data as MovieResponse)
        }
    }

    override suspend fun getPopularMovies(): Result {
        return service.getPopularMovies().mapToPresentation { data ->
            MovieItem.fromResponse(data as MovieResponse)
        }
    }

    override suspend fun getMovieReview(movieId: Int): Result {
        return service.getMovieReview(movieId).mapToPresentation { data ->
            val response = data as MovieReviewResponse
            response.results.map { reviews ->
                MovieReview.from(reviews)
            }
        }
    }

    override suspend fun getTopRatedMovies(): Result {
        return service.getTopRatedMovies().mapToPresentation { data ->
            MovieItem.fromResponse(data as MovieResponse)
        }
    }

    override fun getFavorites() = handleFlowData {
        dao.getFavorites()
    }

    override suspend fun getFavoriteByMovieId(movieId: Int) = callLocalData {
        dao.getFavoriteByMovieId(movieId)
    }.mapToPresentation { data ->
        Favorite.from(data as FavoriteEntity)
    }

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) =
        dao.addFavorite(favoriteEntity)

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int =
        dao.deleteFavorite(favoriteEntity)
}