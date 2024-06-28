package fes.aragon.apppokedex.ui.screens.detail

data class DetailState(
    val pokemon: State = State.Loading,
    val isFavorite: Boolean = false
)
