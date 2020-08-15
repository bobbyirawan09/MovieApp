package bobby.irawan.movieapp.presentation.toprated

import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.emptyMovie
import bobby.irawan.movieapp.helper.MockData.errorResult
import bobby.irawan.movieapp.helper.MockData.movieItems
import bobby.irawan.movieapp.helper.MockData.succesMovieEmptyResult
import bobby.irawan.movieapp.helper.MockData.succesMoviesResult
import bobby.irawan.movieapp.helper.ObserverHelper.getMockObserver
import bobby.irawan.movieapp.presentation.model.MovieItem
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

/**
 * Created by bobbyirawan09 on 15/08/20.
 */
@ExperimentalCoroutinesApi
class TopRatedViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: RepositoryContract

    private lateinit var viewModel: TopRatedViewModel

    override fun setup() {
        super.setup()
        viewModel = TopRatedViewModel(mockRepo)
    }

    @Test
    fun givenNonEmptyResponse_whenGetTopRatedMovie_shouldReturnSuccessWithData() {
        val observer = getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns succesMoviesResult

        // When
        viewModel.getTopRatedMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        assertEquals(
            movieItems,
            viewModel.movieItems.value
        )
    }

    @Test
    fun givenErrorResponse_whenGetTopRatedMovie_shouldReturnErrorWithMessage() {
        val observer = getMockObserver<String>()
        viewModel.snackbarMessage().observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns errorResult

        // When
        viewModel.getTopRatedMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.snackbarMessage().value)
        }
        assertEquals(
            anyString(),
            viewModel.snackbarMessage().value
        )
    }

    @Test
    fun givenEmptyData_whenGetTopRatedMovie_shouldReturnSuccessWithEmptyData() {
        val observer = getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getTopRatedMovies() } returns succesMovieEmptyResult

        // When
        viewModel.getTopRatedMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        assertEquals(
            emptyMovie,
            viewModel.movieItems.value
        )
    }
}