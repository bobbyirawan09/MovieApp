package bobby.irawan.movieapp.presentation.popular

import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.ObserverHelper
import bobby.irawan.movieapp.presentation.model.MovieItem
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.ArgumentMatchers

/**
 * Created by bobbyirawan09 on 15/08/20.
 */
@ExperimentalCoroutinesApi
class PopularViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: RepositoryContract

    private lateinit var viewModel: PopularViewModel

    override fun setup() {
        super.setup()
        viewModel = PopularViewModel(mockRepo)
    }

    @Test
    fun givenNonEmptyResponse_whenGetPopularMovie_shouldReturnSuccessWithData() {
        val observer = ObserverHelper.getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns MockData.succesMoviesResult

        // When
        viewModel.getPopularMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        assertEquals(
            MockData.movieItems,
            viewModel.movieItems.value
        )
    }

    @Test
    fun givenErrorResponse_whenGetPopularMovie_shouldReturnErrorWithMessage() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.snackbarMessage().observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns MockData.errorResult

        // When
        viewModel.getPopularMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.snackbarMessage().value)
        }
        assertEquals(
            ArgumentMatchers.anyString(),
            viewModel.snackbarMessage().value
        )
    }

    @Test
    fun givenEmptyData_whenGetPopularMovie_shouldReturnSuccessWithEmptyData() {
        val observer = ObserverHelper.getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getPopularMovies() } returns MockData.succesMovieEmptyResult

        // When
        viewModel.getPopularMovies()

        // Then
        verifySequence {
            observer.onChanged(viewModel.movieItems.value)
        }
        assertEquals(
            MockData.emptyMovie,
            viewModel.movieItems.value
        )
    }
}