package educ.Iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.databinding.FragmentMoviesBinding
import educ.Iesam.dam2024.features.movies.domain.Movie

class MoviesFragment : Fragment() {


    private lateinit var movieFactory : MovieFactory
    private lateinit var viewModel : MovieViewModel

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!! //esta sobreescribiendo el get() del binding para que devuelva el binding forzado a no null


    private fun setupObserver() {
        val movieObserver = Observer<MovieViewModel.UiState> { uiState ->
            uiState.movies?.let { movies ->
                bindData(movies)
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
        viewModel.uiState.observe(viewLifecycleOwner, movieObserver) //el framento no es un livecycle owner asi que usamos viewLifecycleOwner (no this como en activity)
        //quien es observador (this) y que quiere que se ejecute (movieObserver)

    }

    //Bin es para asignar datos a una vista
    private fun bindData(movies: List<Movie>) {
        //        findViewById<TextView>(R.id.movie_id_1).text = movies[0].id.toString() //ToString Por que es int
        binding.movieId1.text = movies[0].id
        binding.movieTitle1.text = movies[0].title
        binding.layout1.setOnClickListener{
            //findNavController().navigate(R)
        //para acceder al grafo del controlador de navegación
        }

        binding.movieId2.text = movies[1].id
        binding.movieTitle2.text = movies[1].title
        binding.layout2.setOnClickListener{
        }
        binding.movieId1.text = movies[2].id
        binding.movieTitle1.text = movies[2].title
        binding.layout3.setOnClickListener{
        }
        binding.movieId1.text = movies[3].id
        binding.movieTitle1.text = movies[3].title
        binding.layout4.setOnClickListener{
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //En el onCreate view pide asignar al binding la palabra inflate
        //inflate lo que hace es cojer el xml y meterlo dentro de una clase java de donde podemos acceder al los miembros del xml
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        //asociemos la vista que debemos devolver que es el fichero xml inflado
       // Una forma de quitar la interrogación _binding?.movie1 usamos el binding ya que sobreescribe el get()
        //binding.movie1.text = "Hola" //para usarlo ahora
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aqu hacemos el setup
        setupObserver()
        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildMovieModel()
        viewModel.viewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Para evitar fugas de memoria al destruir la vista
    }
    //Es la propia pantalla movieDetail la que crea la instancia (evita duplicar códig
    private fun navigateToMovieDetail(movieId: String){
       // startActivity(MovieDetailFragment.getIntent(, movieId))
    }
}