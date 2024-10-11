package educ.Iesam.dam2024.features.superhero.domain

interface SuperheroRepository {
    fun saveSuperhero(superhero: Superhero)
    fun saveAllSuperheroes(superheroesFromRemote: List<Superhero>)
    suspend fun findAllSuperheroes(): List<Superhero>
    fun findSuperheroById(superheroID : String): Superhero?
    fun deleteAllSuperheroes()
    fun deleteSuperheroById(superheroID : String)
}