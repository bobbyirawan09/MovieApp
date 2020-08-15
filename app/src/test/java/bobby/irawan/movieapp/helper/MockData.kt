package bobby.irawan.movieapp.helper

import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.data.movies.model.MovieResponse
import bobby.irawan.movieapp.data.movies.model.MovieResultResponse
import bobby.irawan.movieapp.data.movies.model.MovieReviewResponse
import bobby.irawan.movieapp.data.movies.model.MovieReviewResultResponse
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.utils.Constants
import bobby.irawan.movieapp.utils.Constants.Result.Error
import bobby.irawan.movieapp.utils.Constants.Result.Success
import kotlinx.coroutines.flow.flowOf
import okhttp3.ResponseBody.Companion.toResponseBody
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 15/08/20.
 */
object MockData {

    //Movie Item
    val movieItem = MovieItem(
        1,
        "Movie Title",
        "http://placehold.jp/48/cccccc/aaaaaa/320x480.png?text=Awesome%20Poster%20Here",
        "http://placehold.jp/36/cccccc/aaaaaa/480x270.png?text=Awesome%20Poster%20Here",
        "02 February 2020",
        "",
        0.0,
        0
    )
    val movieItems = listOf(movieItem)
    val movieResultResponse =
        MovieResultResponse(id = 1, releaseDate = "2020-02-02", title = "Movie Title")
    val movieResponse = MovieResponse(
        null,
        0,
        0,
        listOf(movieResultResponse),
        1,
        10
    )
    val emptyMovieListResponse = MovieResponse(
        null, 0, 0, emptyList(), 0, 0
    )
    val movieSuccessResponse = Response.success(movieResponse)
    val errorMovieListResponse = Response.error<MovieResponse>(400, "".toResponseBody())

    val succesMovieResponseResult = Success(movieResponse)
    val succesMovieResult = Success(movieItem)
    val succesMoviesResult = Success(movieItems)
    val succesMovieEmptyResult = Success(emptyList<MovieItem>())
    val errorResult = Error(anyString())
    val emptyMovie = emptyList<MovieItem>()


    //Movie Review
    val movieReviewResponse = MovieReviewResultResponse(id = "1")
    val movieReviewResponses = MovieReviewResponse(
        0, 1, (0 until 10).map { movieReviewResponse }, 1, 10
    )
    val emptyReviewResponse = MovieReviewResponse(
        0, 1, emptyList(), 0, 0
    )
    val succesEmptyReviewResponse = Success(emptyReviewResponse)
    val succesReviewResponse = Success(movieReviewResponses)

    val movieReview = MovieReview(
        "",
        "",
        "",
        ""
    )
    val movieReviews =
        (0 until 10).map { movieReview.copy(id = "1") }
    val succesEmptyReviewResult = Success<List<MovieReview>>(emptyList())
    val succesReviewResult = Success(movieReview)
    val succesReviewsResult = Success(movieReviews)


    //Favorite Movie
    val favorite = Favorite(
        1,
        "Movie title 1",
        "02 February 2020",
        "",
        "",
        "",
        0,
        0.0
    )
    val favoriteEntity = FavoriteEntity(
        1,
        "Movie title 1",
        "02 February 2020",
        "",
        "",
        "",
        0,
        0.0
    )
    val favoriteMovies = listOf(favorite)

    val favoriteEntities = listOf(favoriteEntity)

    val flowFavoriteMovies = flowOf(favoriteMovies)
    val flowFavoriteEntities = flowOf(favoriteEntities)
    val flowFavoriteEntitiesResult = flowOf(Success(favoriteEntities))

    val flowFavoriteEmptyResult = flowOf(Success<List<Favorite>>(emptyList()))
    val flowFavoriteEmptyEntityResult = flowOf(Success(emptyList<FavoriteEntity>()))
    val flowFavoriteEmptyEntity = flowOf<List<FavoriteEntity>>(emptyList())
    val flowFavoriteResult = flowOf(Success(favoriteMovies))

    val succesEmptyFavoriteResult = Success<List<Favorite>>(emptyList())
    val succesFavoritesResult = Success(favoriteMovies)
    val succesFavoriteResult = Success(favorite)

    fun <T> T.times(times: Int): List<T> {
        return (0 until times).map { this }
    }
}