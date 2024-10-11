package educ.Iesam.dam2024.features.movies.data.remote

import educ.Iesam.dam2024.features.movies.domain.Movie

/*
* Naming: Modelo + Tecnología + RemoteDataSource (o LocalDataSource)
* osea el origen de los datos
* mock: es algo falso, que esta sustituyendo a algo.
* */
class MovieMockRemoteDataSource {

    private val movies = listOf(
        Movie(id = "1", title = "Titanic", poster = ""),
        Movie(id = "2", title = "Avatar", poster = ""),
        Movie(id = "3", title = "Avengers", poster = ""),
        Movie(title = "hola", poster = "", id= "4")
    )
    fun getMovies():List<Movie>{
        return movies
    }

    fun getMovie(movieId: String): Movie? {
        return movies.firstOrNull { movie -> //renombreo it por movie, it es un objeto Movie del listado
         movie.id.equals(movieId) }
    }

}