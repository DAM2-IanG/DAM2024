package educ.Iesam.dam2024.features.superhero.domain

class GetSuperheroByIdUseCase (private val superheroRepository: SuperheroRepository)  {

    fun invoke(superheroId: String): Superhero? {
        return superheroRepository.findSuperheroById(superheroId)
    }

}