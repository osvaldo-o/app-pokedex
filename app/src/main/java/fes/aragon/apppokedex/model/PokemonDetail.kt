package fes.aragon.apppokedex.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val name: String = "",
    val base_experience: Int = 0,
    val weight: Int = 0,
    val height: Int = 0,
    val types: List<TypePokemon> = emptyList()
)

@Serializable
data class TypePokemon(
   val type: PokemonApi
)