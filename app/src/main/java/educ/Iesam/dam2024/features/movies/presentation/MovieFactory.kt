package educ.Iesam.dam2024.features.movies.presentation

import android.content.Context
import educ.Iesam.dam2024.features.movies.data.MovieDataRepository
import educ.Iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import educ.Iesam.dam2024.features.movies.data.remote.MovieMockRemoteDataSource
import educ.Iesam.dam2024.features.movies.domain.GetMovieByIdUseCase
import educ.Iesam.dam2024.features.movies.domain.GetMoviesUseCase

/*Esta clase solo tiene la responsabilidad de crear los objetos que necesitamos.*/
class MovieFactory (private val context: Context){

    private val remoteDataSource = MovieMockRemoteDataSource()
    private val localDataSource = MovieXmlLocalDataSource(context)
    private val movieDataRepository = MovieDataRepository(localDataSource, remoteDataSource)
    private val getMoviesUseCase = GetMoviesUseCase(movieDataRepository)
    private val getMovieByIdUseCase = GetMovieByIdUseCase(movieDataRepository)

    fun buildMovieModel(): MovieViewModel {
           return MovieViewModel(getMoviesUseCase, getMovieByIdUseCase)
    }

    fun buildMovieDetailModel(): MoviesDetailViewModel {
        return MoviesDetailViewModel(getMovieByIdUseCase)
    }
}