package educ.Iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.features.superhero.domain.Appearance
import educ.Iesam.dam2024.features.superhero.domain.Biography
import educ.Iesam.dam2024.features.superhero.domain.Connection
import educ.Iesam.dam2024.features.superhero.domain.Images
import educ.Iesam.dam2024.features.superhero.domain.Powerstat
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import educ.Iesam.dam2024.features.superhero.domain.Work

class SuperheroActivity : AppCompatActivity() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_superhero) //Superhero2 Original

    }
}