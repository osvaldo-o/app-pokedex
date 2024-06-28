package fes.aragon.apppokedex.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fes.aragon.apppokedex.data.repository.Repository
import fes.aragon.apppokedex.model.PokemonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle["id"])
    private val _state = MutableStateFlow(DetailState())
    val uiState = _state.asStateFlow()

    init {
        getData()
    }

    fun onEvent(event: DetailEvent) {
        when(event) {
            is DetailEvent.AddFavorite -> addFavorite(event.name)
            is DetailEvent.DeleteFavorite -> deleteFavorite(event.name)
        }
    }

    private fun addFavorite(name: String) {
        viewModelScope.launch {
            repository.insertPokemon(PokemonEntity(id, name))
            _state.update {
                it.copy(isFavorite = true)
            }
        }
    }

    private fun deleteFavorite(name: String) {
        viewModelScope.launch {
            repository.deletePokemon(PokemonEntity(id, name))
            _state.update {
                it.copy(isFavorite = false)
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            launch {
                isPokemonFavorite()
            }
            launch {
                getPokemon()
            }
        }
    }

    private suspend fun isPokemonFavorite() {
        _state.update {
            it.copy(
                isFavorite = repository.isPokemonFavorite(id)
            )
        }
    }

    private suspend fun getPokemon() {
        try {
            _state.update {
                it.copy(
                    pokemon = State.Success(repository.getPokemonDetail(id))
                )
            }
        } catch (e: IOException) {
            _state.update {
                it.copy(
                    pokemon = State.Error
                )
            }
        }

    }

}

sealed interface State {
    data object Loading: State
    data class Success<T>(val data: T): State
    data object Error: State
}