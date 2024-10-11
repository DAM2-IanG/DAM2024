package educ.Iesam.dam2024.features.movies.domain

// import educ.Iesam.dam2024.features.movies.data.MovieDataRepository NO NO NO DATA
/*Naming: verbo(accción) + UseCase (caso de uso)*/
class GetMoviesUseCase (private val movieRepository: MovieRepository) {

    /*La función que usamos es una de Kotlin llamado invoke(), muy útil para programación funcional (no damos eso)*/
    operator fun invoke() = movieRepository.getMovies()

}