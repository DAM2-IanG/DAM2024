package educ.Iesam.dam2024.features.superhero.domain

class DeleteSuperheroUseCase (private val superheroRepository: SuperheroRepository) {

    fun invoke(superheroId: String) {
        superheroRepository.deleteSuperheroById(superheroId)
    }

}