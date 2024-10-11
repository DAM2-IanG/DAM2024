package educ.Iesam.dam2024.features.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import educ.Iesam.dam2024.app.data.ErrorApp
import educ.Iesam.dam2024.features.movies.domain.GetMovieByIdUseCase
import educ.Iesam.dam2024.features.movies.domain.GetMoviesUseCase
import educ.Iesam.dam2024.features.movies.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

/*ViewModel es una clase de android
* Sirve para registrar la actividad en la pantalla y ejecutar los casos de uso, ademas de recuperar
* los datos que devuelven y pintarlos. (Osea lo que hace interactivo la pantalla)
* Patron: MVVM (Modelo-Vista-ViewModel)
* El viewModel sobrevive a los ciclos de vida de Android. de la Actividad, fragment, etc.
* Una viewMOdel por vista*/
class MovieViewModel (
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase)
    : ViewModel() {


        //pratron del LiveData
        private val _uiState = MutableLiveData<UiState>() //Es un objeto que he creado mutuble, cada datos puede cambiar
        val uiState : LiveData<UiState> = _uiState //Es un objeto que he creado que no puede cambiar.
        //uiState es un objeto que solo se puede leer y no se puede modificar. asi se puede proteger los datos.
        //en el naming usamos _ para indicar que es la privada y no se puede modificar fuera de la clase.
        //uno es privado (_UiState) y otro publico (UiState), el publico lo protegemos haciendo que sea una interfaz (uiState)



    /*Un viewModel tiene como parámetros todos los casos de uso que requiera esa vista.*/

     //Como regla general utilizaremos hilos secundarios para ejectar casos de uso (independientemenete de lo que tarde)





    //cuando se crea la vista
    fun viewCreated() {
        //ViewModelScope es de la libreria, nos permite ejecutar corrutinas
        //Ejecutamos el caso de uso en un hilo secundario
        //Lo hacemos en el hilo por defecto, pero podemos cambiarlo como IO
        //Esto ejecutara en paralelo junto con el main principal.
        //Las corrutinas jamas devolverán nada. Eso es por que el metodo viewCreated se ejecuta por el main pero dentro cambia a otro hilo
        //Pero el hilo principal no espera y sigue su curso, por eso no se devuelve nada.
        //La forma de sincronizar hilos es con los cambios de hilos, que se hacen con cambios de contexto, osea comunicarse con otro hilo
        //Esto se hace con withContext. De paso el hilo dos se ejecuta en paralelo con el hilo principal. Aunque no lo usaremos por el momento.
        //podemos hacerlo con el main

        //para devolver el datos le hacemos un callback a la activity
        viewModelScope.launch(Dispatchers.IO){
                //Vamos usar un patrón observador. LiveData. LiveData tiene una clase
            val movies = getMoviesUseCase.invoke()
            //postValue origen: Default, IO, Main. Destino: Main
            //value origen/destino: El mismo, si vengo del main, devuelvo al main, si vuelvo de default...
            _uiState.postValue(UiState(movies = movies))
                //activity.bindData(movies) podemos usarlo por que hace referencia a la activity main.
                //Pero no es bueno por que crea una dependencia ciclica entre viewModel y Activity. El viewModel no se d
        }
    }
    fun getLocalMovies(): List<Movie> {
        return getMoviesUseCase.invoke();
    }
    fun itemSelected(movieId: String): Movie? {
        return getMovieByIdUseCase.invoke(movieId)
    }

    data class UiState(
        //Esta clase sirve para informar a la clase del estado de la vista.
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null, //ErrorApp es una clase que creamos nosotros
        //ErrorApp sirve para indicar si hay un error en la app o en el servidor, por eso puede ser null, por que puede haber un error en la app o no.
        val movies : List<Movie>? = null //Si no tengo error, es que tengo datos
        //Estos son los estados básicos de la vista, puede haber más. (Estado, Error, datos)
    )
}