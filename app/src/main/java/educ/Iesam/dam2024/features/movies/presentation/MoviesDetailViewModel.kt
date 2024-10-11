package educ.Iesam.dam2024.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.Iesam.dam2024.app.data.ErrorApp
import educ.Iesam.dam2024.features.movies.domain.GetMovieByIdUseCase
import educ.Iesam.dam2024.features.movies.domain.Movie
import kotlinx.coroutines.launch


class MoviesDetailViewModel (
    private val getMovieByIdUseCase: GetMovieByIdUseCase)
    : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun viewCreated(movieId: String) {
       viewModelScope.launch {
           val movie = getMovieByIdUseCase.invoke(movieId)
           _uiState.postValue(UiState(movie = movie))
       }
    }

    data class UiState (
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val movie: Movie? = null
    )
}