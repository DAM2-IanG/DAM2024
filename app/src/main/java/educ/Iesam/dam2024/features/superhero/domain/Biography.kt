package educ.Iesam.dam2024.features.superhero.domain

import java.io.Serializable

data class Biography (val fullName: String, val alterEgos: String, val aliases: List<String>, val placeOfBirth: String, val firstAppearance: String, val publisher: String, val alignment: String) : Serializable{
}