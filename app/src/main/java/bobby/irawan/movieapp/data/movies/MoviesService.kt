package bobby.irawan.movieapp.data.movies

import bobby.irawan.movieapp.utils.Constants.Result

/**
 * Created by bobbyirawan09 on 13/08/20.
 */

interface MoviesService {
    suspend fun getNowPlayingMovies(): Result
    suspend fun getPopularMovies(): Result
    suspend fun getMovieReview(movieId: Int): Result
    suspend fun getTopRatedMovies(): Result
}