package bobby.irawan.movieapp.presentation.detail

import android.content.Intent
import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData
import bobby.irawan.movieapp.helper.MockData.errorResult
import bobby.irawan.movieapp.helper.MockData.favorite
import bobby.irawan.movieapp.helper.MockData.succesEmptyReviewResult
import bobby.irawan.movieapp.helper.MockData.succesReviewsResult
import bobby.irawan.movieapp.helper.ObserverHelper
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.utils.Constants.Result.Success
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

/**
 * Created by bobbyirawan09 on 15/08/20.
 */

@ExperimentalCoroutinesApi
class DetailViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: RepositoryContract

    private lateinit var viewModel: DetailViewModel
    private lateinit var mockIntent: Intent

    override fun setup() {
        super.setup()
        mockIntent = spyk()
        every { mockIntent.getParcelableExtra<MovieItem>(DetailActivity.ITEM_MOVIES) } returns MockData.movieItem
        viewModel = DetailViewModel(mockRepo)
        viewModel.getFromBundle(mockIntent)
    }

    @Test
    fun givenEmptyData_whenGetMovieReview_shouldReturnEmpty() {
        val observer = ObserverHelper.getMockObserver<List<MovieReview>>()
        viewModel.reviews.observeForever(observer)

        // Given
        val response = succesEmptyReviewResult
        coEvery { mockRepo.getMovieReview(any()) } returns response

        // When
        viewModel.getReviews()

        // Then
        verifySequence {
            observer.onChanged(viewModel.reviews.value)
        }
        Assert.assertEquals(response.data, viewModel.reviews.value)
    }

    @Test
    fun givenReviewData_whenGetMovieReview_shouldReturnSuccessWithData() {
        val observer = ObserverHelper.getMockObserver<List<MovieReview>>()
        viewModel.reviews.observeForever(observer)

        // Given
        val response = succesReviewsResult
        coEvery { mockRepo.getMovieReview(any()) } returns response

        // When
        viewModel.getReviews()

        // Then
        verifySequence {
            observer.onChanged(viewModel.reviews.value)
        }
        Assert.assertEquals(response.data, viewModel.reviews.value)
    }

    @Test
    fun givenNonEmptyData_whenGetFavoriteMovie_shouldChangeToTrue() {
        val observer = ObserverHelper.getMockObserver<Boolean>()
        viewModel.isFavorite.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoriteByMovieId(any()) } returns Success(favorite)

        // When
        viewModel.checkIsFavorite()

        // Then
        verifySequence {
            observer.onChanged(true)
        }
    }

    @Test
    fun givenEmptyData_whenGetFavoriteMovie_shouldChangeToFalse() {
        val observer = ObserverHelper.getMockObserver<Boolean>()
        viewModel.isFavorite.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoriteByMovieId(any()) } returns errorResult

        // When
        viewModel.checkIsFavorite()

        // Then
        verifySequence {
            observer.onChanged(false)
        }
    }

    @Test
    fun givenClick_whenClickFavoritedMovie_shouldChangeToFalse() {
        val observer = ObserverHelper.getMockObserver<Boolean>()
        viewModel.isFavorite.observeForever(observer)

        // Given
        coEvery { mockRepo.getFavoriteByMovieId(any()) } returns Success(favorite)
        coEvery { mockRepo.deleteFavorite(any()) } answers { 1 }
        viewModel.checkIsFavorite()

        // When
        viewModel.onFavoriteClick()

        // Then
        verify {
            observer.onChanged(viewModel.isFavorite.value)
        }
        Assert.assertEquals(false, viewModel.isFavorite.value)
    }


}