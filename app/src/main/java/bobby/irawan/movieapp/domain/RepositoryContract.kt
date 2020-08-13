package bobby.irawan.movieapp.domain

import bobby.irawan.movieapp.utils.Constants

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
interface RepositoryContract {
    suspend fun getNowPlayingMovies(): Constants.Result
    suspend fun getPopularMovies(): Constants.Result
    suspend fun getMovieReview(movieId: String): Constants.Result
    suspend fun getTopRatedMovies(): Constants.Result
}