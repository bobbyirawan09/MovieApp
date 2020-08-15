package bobby.irawan.movieapp.presentation.nowplaying

import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.errorResult
import bobby.irawan.movieapp.helper.MockData.succesMovieEmptyResult
import bobby.irawan.movieapp.helper.MockData.succesMoviesResult
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
class NowPlayingViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: RepositoryContract

    private lateinit var viewModel: NowPlayingViewModel

    override fun setup() {
        super.setup()
        viewModel = NowPlayingViewModel(mockRepo)
    }

    @Test
    fun givenNonEmptyResponse_whenGetNowPlayingMovie_shouldReturnSuccessWithData() {
        val observer = ObserverHelper.getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns succesMoviesResult

        // When
        viewModel.getNowPlayingMovies()

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
    fun givenErrorResponse_whenGetNowPlayingMovie_shouldReturnErrorWithMessage() {
        val observer = ObserverHelper.getMockObserver<String>()
        viewModel.snackbarMessage().observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns errorResult

        // When
        viewModel.getNowPlayingMovies()

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
    fun givenEmptyData_whenGetNowPlayingMovie_shouldReturnSuccessWithEmptyData() {
        val observer = ObserverHelper.getMockObserver<List<MovieItem>>()
        viewModel.movieItems.observeForever(observer)

        // Given
        coEvery { mockRepo.getNowPlayingMovies() } returns succesMovieEmptyResult

        // When
        viewModel.getNowPlayingMovies()

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