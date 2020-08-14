package bobby.irawan.movieapp.data.movies

import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.data.movies.model.MovieReviewResponse
import bobby.irawan.movieapp.utils.Constants.PATH_MOVIE
import bobby.irawan.movieapp.utils.Constants.PATH_MOVIE_ID
import bobby.irawan.movieapp.utils.Constants.PATH_NOW_PLAYING
import bobby.irawan.movieapp.utils.Constants.PATH_POPULAR
import bobby.irawan.movieapp.utils.Constants.PATH_REVIEW
import bobby.irawan.movieapp.utils.Constants.PATH_TOP_RATED
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
interface MoviesServiceApi {
    @GET(PATH_MOVIE + PATH_NOW_PLAYING)
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET(PATH_MOVIE + PATH_POPULAR)
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET(PATH_MOVIE + "{" + PATH_MOVIE_ID + "}" + PATH_REVIEW)
    suspend fun getMovieReview(
        @Path(PATH_MOVIE_ID) movieId: Int
    ): Response<MovieReviewResponse>

    @GET(PATH_MOVIE + PATH_TOP_RATED)
    suspend fun getTopRatedMovies(): Response<MovieResponse>
}