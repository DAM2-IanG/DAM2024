package educ.Iesam.dam2024.features.movies.data.local

import android.content.Context
import com.google.gson.Gson
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.features.movies.domain.Movie

class MovieXmlLocalDataSource (private val context: Context) {

    private val gson = Gson()

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.name_file_xml), Context.MODE_PRIVATE)
        //Recurso tipo string + nombre de fichero

    fun save(movie: Movie){
        val editor = sharedPref.edit()
        editor.putString("id", movie.id)
        editor.putString("title", movie.title)
        editor.putString("poster", movie.poster)
        editor.apply() // o commit(), no espera a que se guarde la info y sigue ejecutando.
        //Importante no confundir el apply con el apply de las scope functions
        //sharedPref.edit().apply {
        //    putInt("id", movie.id)
        //    putString("title", movie.title)
        //    putString("poster", movie.poster)
        //    apply()
        //} Version mejor
    }

    fun findMovie(): Movie {
        sharedPref.apply {
            return Movie (
            getString("id", "")!!,
            getString("title", "")!!,
            getString("poster", "")!!
            )
            //Better
        }
    }
    fun findMovie(id : String): Movie? {
        return sharedPref.getString(id, null)?.let {
            gson.fromJson(it, Movie::class.java)
        }
    }

    fun saveAll(movies : List<Movie>){
        val editor = sharedPref.edit()
        movies.forEach { movie ->
            editor.putString(movie.id.toString(), gson.toJson(movie)) //como clave tendra el id de la peli (que es unico) y para su valor vamos a serializar sus estados (variables)
        }
        editor.apply()
    }

    fun findAll(): List<Movie> {
        val movies = mutableListOf<Movie>()
        //Kotlin tienen esta nueva coleccion que es mutableListOf (tambine puedes usar un ArraylIST)
        val mapMovies = sharedPref.all as Map<String , String> //esto es un casting
        //Mapa puedes acceder a claves o valor <clave, valor>
        mapMovies.values.forEach { jsonMovie ->
            val movie = gson.fromJson(jsonMovie, Movie:: class.java ) //pasar json a objeto
            movies.add(movie)
        }
        return movies
    }
    fun deleteMovie(id : String) {
        sharedPref.edit().clear().apply()
    }
}