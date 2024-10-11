package educ.Iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.Iesam.dam2024.app.data.ErrorApp
import educ.Iesam.dam2024.features.movies.domain.Movie
import educ.Iesam.dam2024.features.superhero.domain.GetSuperheroByIdUseCase
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import kotlinx.coroutines.launch

class SuperheroDetailViewModel (
    private val getSuperheroByIdUseCase: GetSuperheroByIdUseCase
): ViewModel()
{
    // MutableLiveData para almacenar la lista de superhéroes
    private val _superheroesUiState = MutableLiveData<UiState>()
    val superheroesUiState: LiveData<UiState> = _superheroesUiState


    fun viewCreated(superheroId: String) {
        viewModelScope.launch {
            _superheroesUiState.postValue(UiState(isLoading = true))
            try {
                val superhero = getSuperheroByIdUseCase.invoke(superheroId)
                _superheroesUiState.postValue(UiState(
                    isLoading = false,
                    superheroes = superhero
                ))
            } catch (e: Exception) {
                //Error
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val superheroes : Superhero? = null
    )
}