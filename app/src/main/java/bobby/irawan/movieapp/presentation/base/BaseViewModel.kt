package bobby.irawan.movieapp.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by bobbyirawan09 on 13/08/20.
 */
abstract class BaseViewModel : ViewModel() {
    protected val snackbarMessage = MutableLiveData<String>()
    fun snackbarMessage() = snackbarMessage as LiveData<String>

    protected fun postSnackbar(message: String) {
        snackbarMessage.postValue(message)
    }
}