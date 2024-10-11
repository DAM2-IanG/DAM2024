package educ.Iesam.dam2024.features.superhero.domain

import java.io.Serializable

data class Appearance (val gender: String, val race: String?, val height: List<String>, val weight: List<String>, val eyeColor: String, val hairColor: String) : Serializable {
}