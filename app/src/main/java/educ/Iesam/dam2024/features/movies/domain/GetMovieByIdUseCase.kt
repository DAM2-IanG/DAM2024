package educ.Iesam.dam2024.features.movies.domain

class GetMovieByIdUseCase (private val movieRepository: MovieRepository){

    operator fun invoke(movieId: String) = movieRepository.getMovie(movieId)

}