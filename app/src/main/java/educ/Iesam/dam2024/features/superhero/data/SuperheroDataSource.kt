package educ.Iesam.dam2024.features.superhero.data

import androidx.lifecycle.LiveData
import educ.Iesam.dam2024.features.superhero.domain.Superhero

interface SuperheroDataSource {
    fun save(superhero: Superhero)
    fun saveAll(superheroesFromRemote: List<Superhero>)
    suspend fun findAll(): List<Superhero>
    fun findById(superheroID : String): Superhero?
    fun deleteAll()
    fun deleteById(superheroID : String)
}