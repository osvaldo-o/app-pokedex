package fes.aragon.apppokedex.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val results: List<PokemonApi>,
)

@Serializable
data class PokemonApi(
    val name: String,
)


