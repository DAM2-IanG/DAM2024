package educ.Iesam.dam2024.features.movies.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.databinding.FragmentMovieDetailBinding
import educ.Iesam.dam2024.features.movies.domain.Movie

class MovieDetailFragment : Fragment() {

    private lateinit var movieFactoru : MovieFactory
    private lateinit var viewModel : MoviesDetailViewModel

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!! //esta sobreescribiendo el get() del binding para que devuelva el binding forzado a no null


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
        viewModel.uiState.observe(viewLifecycleOwner, movieObserver)
        //quien es observador (this) y que quiere que se ejecute (movieObserver)

    }
    //Ya no podemos usar intent.
    private fun getMovieId(): String? {
        return activity?.intent?.getStringExtra(KEY_MOVIE_ID)
    }

    private fun bindData(movie: Movie){
        val text = binding.movie1
        text.text = movie.toString()
        //imageView.loadUrl(movie.poster)
        // Glide.with(this).load(movie.poster).into(imageView)
    }
    //no deberiamos tener librerias por todo el código por que si recibe actualizaciones pues se rompe
    //para ello usamos metodos con extensiones de kotlin




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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

}