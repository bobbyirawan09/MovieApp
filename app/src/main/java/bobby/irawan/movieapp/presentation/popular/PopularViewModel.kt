package bobby.irawan.movieapp.presentation.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.presentation.base.BaseViewModel
import bobby.irawan.movieapp.presentation.model.MovieItem
import bobby.irawan.movieapp.utils.Constants
import kotlinx.coroutines.launch

class PopularViewModel(private val repository: RepositoryContract) : BaseViewModel() {
    private val _movieItems = MutableLiveData<List<MovieItem>>()
    val movieItems = _movieItems as LiveData<List<MovieItem>>

    fun getPopularMovies() {
        viewModelScope.launch {
            val result = repository.getPopularMovies()
            when (result) {
                is Constants.Result.Success<*> -> _movieItems.postValue(result.data as List<MovieItem>)
                is Constants.Result.Error -> postSnackbar(result.message.orEmpty())
            }
        }
    }
}