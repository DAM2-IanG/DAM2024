package educ.Iesam.dam2024.features.superhero.domain

class GetSuperheroesUseCase (private val repository : SuperheroRepository) {

    suspend fun invoke(): List<Superhero> {
        return repository.findAllSuperheroes()
    }

}