package educ.Iesam.dam2024.features.movies.data

import educ.Iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import educ.Iesam.dam2024.features.movies.data.remote.MovieMockRemoteDataSource
import educ.Iesam.dam2024.features.movies.domain.Movie
import educ.Iesam.dam2024.features.movies.domain.MovieRepository

class MovieDataRepository (
    private val localMovieXmlDataSource: MovieXmlLocalDataSource,
    private val mockRemoteDataSource: MovieMockRemoteDataSource) :
    MovieRepository {

    override fun getMovies(): List<Movie> {
        //buscamos memoria local y luego en memoria remota
        val moviesFromLocal = localMovieXmlDataSource.findAll()
        if (moviesFromLocal.isEmpty()) {
            val moviesFromRemote = mockRemoteDataSource.getMovies()
            localMovieXmlDataSource.saveAll(moviesFromRemote)
            return moviesFromRemote
        } else {
            return moviesFromLocal
        }
    }

    //Es más eficiente pedir un objeto por id en la base de datos que filtrarlo. (
    //(Cambiar este metódo a la base de datos)
    override fun getMovie(movieId: String): Movie? {
        //no dependamos de otras funciones
        // NO return getMovies().firstOrNull {it.id == movieId} aqui
        val localMovie = localMovieXmlDataSource.findMovie(movieId)
        if (localMovie == null) {
            mockRemoteDataSource.getMovie(movieId)?.let {
                localMovieXmlDataSource.save(it)
                return it
            }
        }
        return localMovie
    }
}