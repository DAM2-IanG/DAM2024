package educ.Iesam.dam2024.app.data.api

import educ.Iesam.dam2024.features.superhero.domain.Superhero
import retrofit2.http.GET

interface ApiService {
    @GET("all.json")
    suspend fun getSuperheroes(): List<Superhero>

}