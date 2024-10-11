package educ.Iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import educ.Iesam.dam2024.databinding.FragmentSuperheroBinding
import educ.Iesam.dam2024.features.superhero.domain.Superhero

class SuperheroFragment : Fragment() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var superheroViewModel: SuperheroViewModel

    private var _binding: FragmentSuperheroBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        superheroFactory = SuperheroFactory(requireContext())
        superheroViewModel = superheroFactory.buildSuperheroModel()
        setupObserver()
        superheroViewModel.loadSuperheroes()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperheroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObserver(){
        val observer = Observer<SuperheroViewModel.UiState> { uiState ->
            uiState.superheroes?.let { superheroes ->
                bindData(superheroes)
            }

            uiState.errorApp?.let {
                //show error
            } ?: run {                  //Esto es una Scope function de kotlin, Básicamente un if-else
                //hide error
            }
            if (uiState.isLoading) {
                //show loading
            } else {
                //hide loading
            }
        }
        superheroViewModel.superheroesUiState.observe(viewLifecycleOwner, observer)
    }

    private fun bindData(superhero: List<Superhero>) {
        binding.apply {
            layout1.apply {
                superheroName1.text = superhero[0].name
                setOnClickListener{
                    navigateToDetail(superhero[0].id)
                }
            }
        }
        binding.apply {
            layout2.apply {
                superheroName2.text = superhero[1].name
                setOnClickListener{
                    navigateToDetail(superhero[1].id)
                }
            }
        }
        binding.apply {
            layout3.apply {
                superheroName3.text = superhero[2].name
                setOnClickListener{
                    navigateToDetail(superhero[2].id)
                }
            }
        }
        binding.apply {
            layout4.apply {
                superheroName4.text = superhero[3].name
                setOnClickListener{
                    navigateToDetail(superhero[3].id)
                }
            }
        }
       //Most efficient way to bind data to views
    }

    private fun navigateToDetail(superheroID: String) {
        findNavController().navigate(SuperheroFragmentDirections.actionFromSuperheroFragmentToSuperheroDetailFragment(superheroID)) //esto para navegar y pasar el argumento
    }
}