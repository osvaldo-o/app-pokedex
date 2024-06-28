package fes.aragon.apppokedex.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fes.aragon.apppokedex.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val _state = MutableStateFlow(FavoriteState())
    val _pokemonFavporite = repository.getPokemonFavorite()

    val uiState = combine(_state, _pokemonFavporite) { state, pokemonFavorite ->
        state.copy(
            pokemonFavorites = pokemonFavorite
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FavoriteState())

}