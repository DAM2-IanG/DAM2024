package educ.Iesam.dam2024.features.superhero.data.remote

import androidx.activity.result.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import educ.Iesam.dam2024.app.data.api.ApiClient
import educ.Iesam.dam2024.features.superhero.data.SuperheroDataSource
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread

class SuperheroAPIDataSource : SuperheroDataSource{

    private val apiService = ApiClient().apiService


    override fun save(superhero: Superhero) {
        //
    }

    override fun saveAll(superheroesFromRemote: List<Superhero>) {
        //
    }

    override suspend fun findAll() : List<Superhero> {
        return try {
            apiService.getSuperheroes()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override fun findById(superheroID: String): Superhero? {
        //
        return null;
    }

    override fun deleteAll() {
        //
    }

    override fun deleteById(superheroID: String) {
        //
    }

}