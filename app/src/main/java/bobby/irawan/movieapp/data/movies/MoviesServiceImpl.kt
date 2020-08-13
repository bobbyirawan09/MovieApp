package bobby.irawan.movieapp.data.movies

import bobby.irawan.movieapp.utils.Constants

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class MoviesServiceImpl(private val api: MoviesServiceApi) : MoviesServiceContract {
    override suspend fun getNowPlayingMovies(): Constants.Result {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): Constants.Result {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieReview(movieId: String): Constants.Result {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): Constants.Result {
        TODO("Not yet implemented")
    }
}