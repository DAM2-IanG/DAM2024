package educ.Iesam.dam2024.features.movies.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.app.data.ErrorApp
import educ.Iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import educ.Iesam.dam2024.features.movies.domain.Movie

class MoviesActivity : AppCompatActivity() {


    /*Creacion de dependencia con la clase MovieViewModel*/
    /*MovieFactory instancia la clase MovieViewModel*/
    //NO : private val movieFactory = MovieFactory(this ) por que this es null ya que el super en onCreate crea la instancia


    private lateinit var movieFactory : MovieFactory //lateinit es para inicializarla mas tarde pero no es buena práctica (minimzar su uso)
    private lateinit var viewModel : MovieViewModel
    /*Con Injector de dependencias esto te lo hace automaticamente(más adelante)*/


    //Principios SOLID, Open-Close
    //la arquitectura open-close es una de las reglas de SOLID


    //Android por debajo controla que métodos se ejecutan según el estado de la app.
    //Osea, metódos que se denominan ciclos de vida.
    // Aqui se usa otra estructura que es la inversión del control (DI), osea Android decide que metodos ejecutar a traves
    // de los metodos de ciclo de vida. Hay es donde meto código pero es el framework quien va ejecutando los métodos.

    //El método onCreate es cuando se crea la actividad. Despues pasa a otro ciclo de vida siendo onStart, el usuario ve la pantalla
    //Pero no puede interacturar con ella todavia. depues pasa en onResume es que la pantalla se visualiza y el usuario ya puede interacturar.
    // ViewMode sobrevive a cambios de configuración (rotación y hasta onDestroy). ViewModel solo muere con onCleared().
    // Esto es muy importante ya que asi hacemos la app más eficiente a cambios de configuración.
    // Con cada cambio, se piden datos y se destruyen y crean hilos lo que consume recursos.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //este crea la instancia de la clase MovieActivity
        setContentView(R.layout.activity_movies) /*Aqui se elige la vista que queremos que se muestre (interfaz*/
        /* R son recursos (res) en android
        * EN LAYOUT dentro de res tenemos activity_main.xml, aqui es donde trabajaremos con las interfaces
        * echale un ojo a xml*/
    }

}