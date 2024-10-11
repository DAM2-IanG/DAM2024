package educ.Iesam.dam2024.features.superhero.domain

class DeleteAllSuperheroesUseCase (private val superheroRepository: SuperheroRepository) {

    fun invoke() {
        superheroRepository.deleteAllSuperheroes()
    }
}