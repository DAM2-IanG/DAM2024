package educ.Iesam.dam2024.features.superhero.presentation


import android.content.Context
import educ.Iesam.dam2024.features.superhero.data.SuperheroDataRepository
import educ.Iesam.dam2024.features.superhero.data.local.SuperheroXmlDataSource
import educ.Iesam.dam2024.features.superhero.data.remote.SuperheroAPIDataSource
import educ.Iesam.dam2024.features.superhero.domain.DeleteAllSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.DeleteSuperheroUseCase
import educ.Iesam.dam2024.features.superhero.domain.GetSuperheroByIdUseCase
import educ.Iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.SaveAllSuperheroesUseCase
import educ.Iesam.dam2024.features.superhero.domain.SaveSuperheroUseCase

class SuperheroFactory (context: Context) {

    private val localDataSource = SuperheroXmlDataSource(context)
    private val remoteDataSource = SuperheroAPIDataSource()
    private val superheroDataRepository = SuperheroDataRepository(localDataSource, remoteDataSource)

    private val getSuperheroesUseCase = GetSuperheroesUseCase(superheroDataRepository)
    private val getSuperheroByIdUseCase = GetSuperheroByIdUseCase(superheroDataRepository)
    private val saveSuperheroUseCase = SaveSuperheroUseCase(superheroDataRepository)
    private val saveAllSuperheroesUseCase = SaveAllSuperheroesUseCase(superheroDataRepository)
    private val deleteSuperheroUseCase = DeleteSuperheroUseCase(superheroDataRepository)
    private val deleteAllSuperheroesUseCase = DeleteAllSuperheroesUseCase(superheroDataRepository)


    fun buildSuperheroModel(): SuperheroViewModel {
        return SuperheroViewModel(
            getSuperheroesUseCase,
            getSuperheroByIdUseCase,
            saveSuperheroUseCase,
            saveAllSuperheroesUseCase,
            deleteSuperheroUseCase,
            deleteAllSuperheroesUseCase
        )
    }

    fun buildSuperheroDetailModel(): SuperheroDetailViewModel {
        return SuperheroDetailViewModel(getSuperheroByIdUseCase)
    }

}