package educ.Iesam.dam2024.features.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.app.extensions.loadUrl
import educ.Iesam.dam2024.features.movies.domain.Movie

class MovieDetailActivity : AppCompatActivity() {


    private lateinit var movieFactory : MovieFactory
    private lateinit var viewModel : MoviesDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_detail)

        movieFactory = MovieFactory(this)
        viewModel = movieFactory.buildMovieDetailModel()

    }
/*
    private fun setupObserver() {
        val movieObserver = Observer<MoviesDetailViewModel.UiState> { uiState ->
            uiState.movie?.let { movie ->
                bindData(movie)
            }
            uiState.errorApp?.let {
                //show error
            }
            if (uiState.isLoading){
                //show loading
            } else {
                //hide loading
            }
        }
        viewModel.uiState.observe(this, movieObserver)
        //quien es observador (this) y que quiere que se ejecute (movieObserver)

    }
    private fun getMovieId(): String? {
        return intent.getStringExtra(KEY_MOVIE_ID)
    }

    private fun bindData(movie: Movie){
        val text = findViewById<TextView>(R.id.movie_1)
        text.text = movie.toString()
        //imageView.loadUrl(movie.poster)
       // Glide.with(this).load(movie.poster).into(imageView)
    }
    //no deberiamos tener librerias por todo el código por que si recibe actualizaciones pues se rompe
    //para ello usamos metodos con extensiones de kotlin




    //Funcion estática (pertenece a la clase) , por convencion se crean abajo de la clase
    companion object {
        val KEY_MOVIE_ID = "KEY_MOVIE_ID" //clave para identificar el objeto que se ha seleccionado
        //Esta funcion crea el intent para navegar a su pantalla
        fun getIntent(context : Context, movieId: String) : Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(KEY_MOVIE_ID, movieId)
            //intent.putExtra("KEY_MOVIE_ID", movieId) sirve para saber que objeto se ha seleccionado
            return intent
        }
    }
 */
}