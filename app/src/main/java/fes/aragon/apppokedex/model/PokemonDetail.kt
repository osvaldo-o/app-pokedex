package fes.aragon.apppokedex.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val order: Int,
    val name: String,
    val base_experience: Int,
    val weight: Int,
    val height: Int,
    val type: TypePokemon
)

@Serializable
data class TypePokemon(
    val types: List<PokemonApi>
)