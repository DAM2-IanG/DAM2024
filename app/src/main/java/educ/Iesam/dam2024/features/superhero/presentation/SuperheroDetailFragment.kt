package educ.Iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import educ.Iesam.dam2024.databinding.FragmentMovieDetailBinding
import educ.Iesam.dam2024.databinding.FragmentSuperheroDetailsBinding
import educ.Iesam.dam2024.features.superhero.domain.Superhero

class SuperheroDetailFragment : Fragment() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var superheroDetailViewModel: SuperheroDetailViewModel

    // Accede a los argumentos pasados al fragmento
    private val superheroArgs: SuperheroDetailFragmentArgs by navArgs() // Accede a los argumentos pasados al fragmento

    private var _binding : FragmentSuperheroDetailsBinding? = null
    private val binding get() =_binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superheroFactory = SuperheroFactory(this.requireContext())
        superheroDetailViewModel = superheroFactory.buildSuperheroDetailModel()
        val superheroTest = superheroDetailViewModel.viewCreated(superheroArgs.superheroID)
        setupObserver()
    }

    private fun setupObserver() {
        superheroDetailViewModel.superheroesUiState.observe(viewLifecycleOwner) { uiState ->
            uiState.superheroes?.let { superhero ->
                bindData(superhero)
            }
        }
    }
    private fun bindData(superhero: Superhero) {
        binding.apply {
            superheroName.text = superhero.name
            superheroSlug.text = superhero.slug
            superheroStrength.text = "Strength: ${superhero.powerstats.strength}"
            superheroSpeed.text = "Speed: ${superhero.powerstats.speed}"
            superheroDurability.text ="Durabillity ${superhero.powerstats.durability}"
            superheroPower.text = "Power: ${superhero.powerstats.power}"
            superheroCombat.text = "Combat: ${superhero.powerstats.combat}"

            superheroBiography.text = "Lugar de nacimiento: ${superhero.biography.placeOfBirth}"
            superheroBiography.text = "Primera aparición: ${superhero.biography.firstAppearance}"
            superheroBiography.text = "Editorial: ${superhero.biography.publisher}"

            superheroWork.text = "Ocupación: ${superhero.work.occupation}"
            superheroWork.text = "Base de operaciones: ${superhero.work.base}"

            superheroConnections.text = "Grupos afiliados: ${superhero.connections.groupAffiliation}"
            superheroConnections.text = "Relaciones: ${superhero.connections.relatives}"
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}