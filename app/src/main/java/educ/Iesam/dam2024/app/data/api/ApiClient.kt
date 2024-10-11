package educ.Iesam.dam2024.app.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val URL_BASE = "https://akabab.github.io/superhero-api/api/"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(URL_BASE).
            addConverterFactory(GsonConverterFactory.create())
                .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}