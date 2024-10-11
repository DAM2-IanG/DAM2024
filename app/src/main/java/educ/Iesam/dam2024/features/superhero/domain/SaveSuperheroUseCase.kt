package educ.Iesam.dam2024.features.superhero.domain

class SaveSuperheroUseCase (private val superheroRepository: SuperheroRepository) {

    fun  invoke(superhero: Superhero) {
        superheroRepository.saveSuperhero(superhero)
    }

}