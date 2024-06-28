package fes.aragon.apppokedex.ui.screens.detail

sealed interface DetailEvent {
    data class AddFavorite(val name: String): DetailEvent
    data class DeleteFavorite(val name: String): DetailEvent
}