package educ.Iesam.dam2024.features.movies.domain

interface MovieRepository {

    fun getMovies(): List<Movie>

    fun getMovie(movieId: String): Movie?
}