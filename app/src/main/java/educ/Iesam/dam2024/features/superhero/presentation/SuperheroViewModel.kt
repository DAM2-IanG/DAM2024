package educ.Iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.Iesam.dam2024.app.data.ErrorApp
import educ.Iesam.dam2024.features.superhero.domain.DeleteAllSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.DeleteSuperheroUseCase
import educ.Iesam.dam2024.features.superhero.domain.GetSuperheroByIdUseCase
import educ.Iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.SaveAllSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.SaveSuperheroUseCase
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SuperheroViewModel (
    private val getSuperheroesUseCase: GetSuperheroesUseCase,
    private val getSuperheroByIdUseCase: GetSuperheroByIdUseCase,
    private val saveSuperheroUseCase: SaveSuperheroUseCase,
    private val saveAllSuperheroesUseCase: SaveAllSuperheroesUseCase,
    private val deleteSuperheroUseCase: DeleteSuperheroUseCase,
    private val deleteAllSuperheroesUseCase: DeleteAllSuperheroesUseCase,): ViewModel()
{
    // MutableLiveData para almacenar la lista de superhéroes
    private val _superheroesUiState = MutableLiveData<UiState>()
    val superheroesUiState: LiveData<UiState> = _superheroesUiState

    // MutableLiveData para manejar posibles errores
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    // Función para cargar los superheroes
    fun loadSuperheroes() {
        // Lanza una coroutine en el ViewModelScope para que se ejecute en segundo plano
        viewModelScope.launch (Dispatchers.IO) {
            // Actualizamos el estado a "cargando"
            _superheroesUiState.postValue(UiState(isLoading = true))

            try {
                // Ejecuta el caso de uso para obtener los superhéroes
                val superheroes = getSuperheroesUseCase.invoke()
                // Actualiza el estado con los superhéroes obtenidos
                _superheroesUiState.postValue(UiState(
                    isLoading = false,
                    superheroes = superheroes
                ))

            } catch (e: Exception) {
                // En caso de error, actualizamos el estado con el error correspondiente
                // Aquí puedes crear una instancia de ErrorApp si tienes una clase definida
                _superheroesUiState.postValue(
                    UiState(
                        isLoading = false // Agrega el error a la UI state
                    )
                )
            }
        }
    }

    fun itemSelected(superheroID: String): Superhero? {
        return getSuperheroByIdUseCase.invoke(superheroID)
    }

    fun saveSuperhero(superhero: Superhero) {
        saveSuperheroUseCase.invoke(superhero)
    }
    fun getSuperheroById(superheroID: String): Superhero? {
        return getSuperheroByIdUseCase.invoke(superheroID)
    }
    fun deleteSuperhero(superheroID: String) {
        deleteSuperheroUseCase.invoke(superheroID)
    }
    fun deleteAllSuperheroes() {
        deleteAllSuperheroesUseCase.invoke()

    }
    fun saveAllSuperheroes(superheroesFromRemote: List<Superhero>) {
        saveAllSuperheroesUseCase.invoke(superheroesFromRemote)

    }
    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val superheroes : List<Superhero>? = null
    )
}