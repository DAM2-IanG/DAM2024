package educ.Iesam.dam2024.features.superhero.domain

class SaveAllSuperheroesUseCase (private val superheroRepository: SuperheroRepository) {

    fun invoke(superheroesFromRemote: List<Superhero>) {
        superheroRepository.saveAllSuperheroes(superheroesFromRemote)
    }
}