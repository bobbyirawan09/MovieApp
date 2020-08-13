package bobby.irawan.movieapp.data.movies

import bobby.irawan.movieapp.utils.Constants.Result
import bobby.irawan.movieapp.utils.callApi

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class MoviesServiceImpl(private val api: MoviesServiceApi) : MoviesServiceContract {
    override suspend fun getNowPlayingMovies(): Result {
        return callApi { api.getNowPlayingMovies() }
    }

    override suspend fun getPopularMovies(): Result {
        return callApi { api.getPopularMovies() }
    }

    override suspend fun getMovieReview(movieId: String): Result {
        return callApi { api.getMovieReview(movieId) }
    }

    override suspend fun getTopRatedMovies(): Result {
        return callApi { api.getTopRatedMovies() }
    }
}