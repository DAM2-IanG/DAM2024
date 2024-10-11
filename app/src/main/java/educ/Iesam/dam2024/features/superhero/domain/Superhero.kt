package educ.Iesam.dam2024.features.superhero.domain

import java.io.Serializable

data class Superhero (val id: String, val name: String, val slug: String, val powerstats:Powerstat
                      , val appearance: Appearance, val biography: Biography, val work: Work, val connections: Connection, val images: Images) : Serializable {
}