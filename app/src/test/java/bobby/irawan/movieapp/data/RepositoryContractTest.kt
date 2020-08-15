package bobby.irawan.movieapp.presentation.data

import bobby.irawan.movieapp.data.favorite.FavoriteDao
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.data.movies.MoviesService
import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.domain.RepositoryImpl
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.favoriteEntities
import bobby.irawan.movieapp.helper.MockData.favoriteEntity
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEmptyEntity
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEmptyEntityResult
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEntities
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEntitiesResult
import bobby.irawan.movieapp.helper.MockData.succesFavoriteResult
import bobby.irawan.movieapp.helper.MockData.succesMovieResponseResult
import bobby.irawan.movieapp.helper.MockData.succesMoviesResult
import bobby.irawan.movieapp.helper.MockData.succesReviewResponse
import bobby.irawan.movieapp.helper.MockData.succesReviewsResult
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.utils.Constants
import bobby.irawan.movieapp.utils.Constants.Result.Success
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

/**
 * Created by bobbyirawan09 on 15/08/20.
 */
@ExperimentalCoroutinesApi
class RepositoryContractTest : BaseTest() {

    @MockK
    private lateinit var mockService: MoviesService

    @MockK
    private lateinit var mockDao: FavoriteDao

    private lateinit var repository: RepositoryContract

    override fun setup() {
        super.setup()
        repository = RepositoryImpl(mockService, mockDao)
    }

    @Test
    fun givenSuccessResponse_whenGetPopularMovie_shouldReturnSuccessResult() {
        // Given
        val response = succesMoviesResult
        coEvery { mockService.getPopularMovies() } returns succesMovieResponseResult

        runBlockingTest {
            // When
            val result = repository.getPopularMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun givenSuccessResponse_whenGetTopRatedMovie_shouldReturnSuccessResult() {
        // Given
        val response = succesMoviesResult
        coEvery { mockService.getTopRatedMovies() } returns succesMovieResponseResult

        runBlockingTest {
            // When
            val result = repository.getTopRatedMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun givenSuccessResponse_whenGetNowPlayingMovie_shouldReturnSuccessResult() {
        // Given
        val response = succesMoviesResult
        coEvery { mockService.getNowPlayingMovies() } returns succesMovieResponseResult

        runBlockingTest {
            // When
            val result = repository.getNowPlayingMovies()
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun givenSuccessResponse_whenGetMovieReview_shouldReturnSuccessResult() {
        // Given
        val response = succesReviewResponse
        coEvery { mockService.getMovieReview(any()) } returns response

        runBlockingTest {
            // When
            val result = repository.getMovieReview(123)
            // Then
            Assert.assertEquals(succesReviewsResult, result)
        }
    }

    @Test
    fun givenSuccessResponse_whenGetFavoriteMovieById_shouldReturnSuccessResult() {
        // Given
        val response = succesFavoriteResult
        coEvery { mockDao.getFavoriteByMovieId(any()) } returns favoriteEntity

        runBlockingTest {
            // When
            val result = repository.getFavoriteByMovieId(123)
            // Then
            Assert.assertEquals(response, result)
        }
    }

    @Test
    fun givenZeroResponse_whenGetFavoriteMovieById_shouldReturnSuccessResultWithNoData() {
        // Given
        coEvery { mockDao.getFavoriteByMovieId(any()) } returns FavoriteEntity()

        runBlockingTest {
            // When
            val result = repository.getFavoriteByMovieId(123)
            // Then
            Assert.assertEquals(Success(Favorite()), result)
        }
    }

    @Test
    fun givenSuccessResponse_whenGetFavoriteMovie_shouldReturnSuccessResult() {
        // Given
        val response = flowOf(favoriteEntities)
        every { mockDao.getFavorites() } returns response

        runBlockingTest {
            // When
            val result = repository.getFavorites()
            // Then
            Assert.assertEquals(flowOf(Success(favoriteEntities)), result)
        }
    }

    @Test
    fun givenZeroResponse_whenGetFavoriteMovie_shouldReturnSuccessResultWithNoData() {
        // Given
        every { mockDao.getFavorites() } returns flowFavoriteEmptyEntity

        runBlockingTest {
            // When
            val result = repository.getFavorites()
            // Then
            Assert.assertEquals(flowFavoriteEmptyEntityResult, result)
        }
    }

    @Test
    fun givenFavoriteData_whenAddFavoriteMovie_shouldReturnNothing() {
        // Given
        val input = favoriteEntity
        coEvery { mockDao.addFavorite(any()) } coAnswers { println("Inserting $input") }

        runBlockingTest {
            // When
            repository.addFavorite(input)

            // Then
            coVerify { mockDao.addFavorite(input) }
        }
    }

    @Test
    fun givenFavoriteData_whenDeleteFavoriteMovie_shouldReturnOne() {
        // Given
        val input = favoriteEntity
        coEvery { mockDao.deleteFavorite(any()) } coAnswers { 1 }

        runBlockingTest {
            // When
            repository.deleteFavorite(input)

            // Then
            coVerify { mockDao.deleteFavorite(input) }
        }
    }


}