package bobby.irawan.movieapp.presentation.favorite

import bobby.irawan.movieapp.domain.RepositoryImpl
import bobby.irawan.movieapp.helper.BaseTest
import bobby.irawan.movieapp.helper.MockData.favoriteMovies
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEmptyResult
import bobby.irawan.movieapp.helper.MockData.flowFavoriteEntitiesResult
import bobby.irawan.movieapp.helper.ObserverHelper.getMockObserver
import bobby.irawan.movieapp.presentation.model.Favorite
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

/**
 * Created by bobbyirawan09 on 15/08/20.
 */

@ExperimentalCoroutinesApi
class FavoritesViewModelTest : BaseTest() {

    @MockK
    private lateinit var mockRepo: RepositoryImpl

    private lateinit var viewModel: FavoriteViewModel


    @Test
    fun givenEmptyData_whenGetFavoriteMovie_shouldReturnEmptyData() {
        // Given
        coEvery { mockRepo.getFavorites() } returns flowFavoriteEmptyResult
        viewModel = FavoriteViewModel(mockRepo)

        val observer = getMockObserver<List<Favorite>>()
        viewModel.favoriteResult.observeForever(observer)

        //When
        viewModel.getAllFavourite()

        // Then
        Assert.assertEquals(
            emptyList<Favorite>(),
            viewModel.favoriteResult.value
        )
    }

    @Test
    fun givenNonEmptyData_whenGetFavoriteMovie_shouldReturnFavoriteData() {
        // Given
        coEvery { mockRepo.getFavorites() } returns flowFavoriteEntitiesResult
        viewModel = FavoriteViewModel(mockRepo)

        val observer = getMockObserver<List<Favorite>>()
        viewModel.favoriteResult.observeForever(observer)

        //When
        viewModel.getAllFavourite()

        // Then
        Assert.assertEquals(
            favoriteMovies,
            viewModel.favoriteResult.value
        )
    }

}