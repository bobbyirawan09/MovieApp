package bobby.irawan.movieapp.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import bobby.irawan.movieapp.AppController
import bobby.irawan.movieapp.R
import bobby.irawan.movieapp.data.favorite.model.FavoriteEntity
import bobby.irawan.movieapp.domain.RepositoryContract
import bobby.irawan.movieapp.presentation.base.BaseViewModel
import bobby.irawan.movieapp.presentation.model.Favorite
import bobby.irawan.movieapp.utils.Constants
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

class FavoriteViewModel(
    private val repository: RepositoryContract
) : BaseViewModel() {

    private var _favoriteResult = MutableLiveData<List<Favorite>>()
    val favoriteResult = _favoriteResult as LiveData<List<Favorite>>

    fun getAllFavourite() {
        viewModelScope.launch {
            repository.getFavorites()
                .catch { e ->
                    postSnackbar(e.localizedMessage.orEmpty())
                }
                .collect { result ->
                    when (result) {
                        is Constants.Result.Success<*> -> {
                            val data = result.data as List<FavoriteEntity>
                            val presentationData = data.map { favoriteEntities ->
                                Favorite.from(favoriteEntities)
                            }
                            _favoriteResult.postValue(presentationData)
                        }
                        is Error -> postSnackbar(result.message.orEmpty())
                    }
                }
        }
    }
}