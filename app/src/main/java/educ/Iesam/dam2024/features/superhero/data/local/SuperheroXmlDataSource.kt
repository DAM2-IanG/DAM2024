package educ.Iesam.dam2024.features.superhero.data.local

import android.content.Context
import educ.Iesam.dam2024.R
import educ.Iesam.dam2024.features.superhero.data.SuperheroDataSource
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import com.google.gson.Gson


class SuperheroXmlDataSource (private val context: Context) : SuperheroDataSource {

    private val sharedPref = context.getSharedPreferences (context.getString(R.string.superhero_file_xml), Context.MODE_PRIVATE)

    private val gson = Gson()

    override fun save(superhero: Superhero) { //Hace la funcion de update tambien
        sharedPref.edit().apply {
            putString(superhero.id, gson.toJson(superhero))
            apply()
        }
    }

    override fun saveAll(superheroesFromRemote: List<Superhero>) {
        sharedPref.edit().apply {
            superheroesFromRemote.forEach { superhero ->
                putString(superhero.id, gson.toJson(superhero))
            }
            apply()
        }
    }

    override suspend fun findAll(): List<Superhero> {
        val superheroes = mutableListOf<Superhero>()

        sharedPref.all.apply {
            this.values.forEach { jsonSuperhero ->
                val superhero = gson.fromJson(jsonSuperhero as String, Superhero::class.java)
                superheroes.add(superhero)
            }
        }
        return superheroes
    }

    override fun findById(superheroID: String): Superhero? {
        sharedPref.apply {
            return gson.fromJson(getString(superheroID, ""), Superhero::class.java)
        }
        //return sharedPref.getString(superheroID, null)?.let { gson.fromJson(it, Superhero::class.java) }
    }

    override fun deleteAll() {
        sharedPref.edit().clear().apply()
    }

    override fun deleteById(superheroID: String) {
        sharedPref.edit().remove(superheroID).apply()
    }
}