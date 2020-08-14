package bobby.irawan.movieapp.presentation.detail

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.presentation.base.BaseViewModel
import bobby.irawan.movieapp.presentation.detail.DetailActivity.Companion.ITEM_MOVIES
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.presentation.model.MovieReview
import bobby.irawan.movieapp.utils.Constants.Result.Error
import bobby.irawan.movieapp.utils.Constants.Result.Success
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
class DetailViewModel(private val repository: RepositoryContract) : BaseViewModel() {

    private lateinit var movie: MovieItem

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite = _isFavorite as LiveData<Boolean>

    private var _reviews = MutableLiveData<List<MovieReview>>()
    val reviews = _reviews as LiveData<List<MovieReview>>

    private var _movieItem = MutableLiveData<MovieItem>()
    val movieItem = _movieItem as LiveData<MovieItem>

    fun getFromBundle(intent: Intent?) {
        movie = intent?.getParcelableExtra(ITEM_MOVIES) as MovieItem
        _movieItem.postValue(movie)
    }

    fun onFavoriteClick() {
        if (_isFavorite.value == true) {
            deleteFromFavorite()
        } else {
            addToFavorite()
        }
    }

    fun getReviews() {
        viewModelScope.launch {
            val result = repository.getMovieReview(movie.id)
            when (result) {
                is Success<*> -> {
                    _reviews.postValue(result.data as List<MovieReview>)
                }
                is Error -> postSnackbar(result.message.orEmpty())
            }
        }
    }

    fun checkIsFavorite() {
        viewModelScope.launch {
            val result = repository.getFavoriteByMovieId(movie.id)
            when (result) {
                is Success<*> -> {
                    val movieFavorite = result.data as Favorite
                    if (movieFavorite.movieId != 0) {
                        _isFavorite.postValue(true)
                    }
                }
                is Error -> _isFavorite.postValue(false)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            repository.addFavorite(FavoriteEntity.from(movie))
            _isFavorite.postValue(true)
        }
    }

    private fun deleteFromFavorite() {
        viewModelScope.launch {
            val result = repository.deleteFavorite(FavoriteEntity.from(movie))
            if (result > 0) {
                _isFavorite.postValue(false)
                postSnackbar("Deleted from favorite list")
            }
        }
    }
}