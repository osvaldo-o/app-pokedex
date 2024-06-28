package fes.aragon.apppokedex.ui.screens.favorite

import fes.aragon.apppokedex.model.PokemonEntity

data class FavoriteState(
    val pokemonFavorites: List<PokemonEntity> = emptyList()
)
