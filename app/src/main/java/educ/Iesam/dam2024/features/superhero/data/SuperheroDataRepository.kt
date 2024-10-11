package educ.Iesam.dam2024.features.superhero.data

import educ.Iesam.dam2024.features.superhero.domain.Appearance
import educ.Iesam.dam2024.features.superhero.domain.Biography
import educ.Iesam.dam2024.features.superhero.domain.Connection
import educ.Iesam.dam2024.features.superhero.domain.Images
import educ.Iesam.dam2024.features.superhero.domain.Powerstat
import educ.Iesam.dam2024.features.superhero.domain.Superhero
import educ.Iesam.dam2024.features.superhero.domain.SuperheroRepository
import educ.Iesam.dam2024.features.superhero.domain.Work

class SuperheroDataRepository (

    private val localDataSource: SuperheroDataSource,
    private val remoteDataSource: SuperheroDataSource) : SuperheroRepository {


    //Mock of Data
    private val powerstats = Powerstat (38, 100, 17, 80, 24, 64)
    private val height = listOf("feet", "centimeters")
    private val weight = listOf("pounds", "kilograms")
    private val appearance = Appearance ("Gender", "Race",height,weight,
        "Eye_Color", "Hair_Color")
    private val biography = Biography ("FullName", "AlterEgos",
        listOf("Publisher1", "Publisher2"), "Place of Birth", "Debut",
        "Publisher", "alignment")
    private val work = Work ("occupation", "base")
    private val connections = Connection ("groupAffiliation", "Relatives")
    private val images = Images ("httpXs", "httpSm", "httpMd", "httpLg")

    private val superheroes = listOf(
        Superhero("1", "Name1", "Slug1", powerstats, appearance, biography, work, connections, images),
        Superhero ("2", "Name2", "slug2", powerstats, appearance, biography, work, connections, images)
    )

    override fun saveSuperhero(superhero: Superhero) {
        localDataSource.save(superhero)
        remoteDataSource.save(superhero)
    }

    override fun saveAllSuperheroes(superheroesFromRemote: List<Superhero>) {
        localDataSource.saveAll(superheroesFromRemote)
        remoteDataSource.saveAll(superheroesFromRemote)
    }

    override suspend fun findAllSuperheroes(): List<Superhero> {
        // Primero intenta obtener los datos locales
        val localSuperheroes = localDataSource.findAll()

        // Si hay datos en la base de datos local, los devuelve
        return if (localSuperheroes.isNotEmpty()) {
            localSuperheroes
        } else {
            // Si no hay datos locales, obtiene los datos de la API remota
            val remoteSuperheroes = remoteDataSource.findAll()

            // Guarda los datos remotos en la base de datos local para futuras consultas
            if (remoteSuperheroes.isNotEmpty()) {
                localDataSource.saveAll(remoteSuperheroes)
            }
            // Devuelve los datos remotos
            remoteSuperheroes
        }
    }

    override fun findSuperheroById(superheroID: String): Superhero? {
        val superheroFromLocal = localDataSource.findById(superheroID)
        if (superheroFromLocal != null) {
            return superheroFromLocal
        } else {
            val superheroFromRemote = remoteDataSource.findById(superheroID)
            if (superheroFromRemote != null) {
                localDataSource.save(superheroFromRemote)
                return superheroFromRemote
            }
        }
        return null
    }

    override fun deleteAllSuperheroes() {
        localDataSource.deleteAll()
        remoteDataSource.deleteAll()
    }

    override fun deleteSuperheroById(superheroID: String) {
        localDataSource.deleteById(superheroID)
        remoteDataSource.deleteById(superheroID)

    }


}