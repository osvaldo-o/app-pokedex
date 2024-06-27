package fes.aragon.apppokedex.ui.screens.home

import fes.aragon.apppokedex.model.PokemonApi

sealed interface HomeState {
    data object Loading: HomeState
    data class Success(val pokemon: List<PokemonApi>): HomeState
    data object Error: HomeState
}