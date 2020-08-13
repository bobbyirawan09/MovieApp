package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.data.movies.MoviesServiceContract
import bobby.irawan.movieapp.utils.Constants
import bobby.irawan.movieapp.utils.mapToPresentation

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class RepositoryImpl(private val service: MoviesServiceContract) :
    RepositoryContract {
    override suspend fun getNowPlayingMovies(): Constants.Result {
        return mapToPresentation { service.getNowPlayingMovies() }
    }

    override suspend fun getPopularMovies(): Constants.Result {
        return mapToPresentation { service.getPopularMovies() }
    }

    override suspend fun getMovieReview(movieId: String): Constants.Result {
        return mapToPresentation { service.getMovieReview(movieId) }
    }

    override suspend fun getTopRatedMovies(): Constants.Result {
        return mapToPresentation { service.getTopRatedMovies() }
    }
}