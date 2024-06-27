package fes.aragon.apppokedex.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fes.aragon.apppokedex.data.repository.Repository
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    var uiState: HomeState by mutableStateOf(HomeState.Loading)
        private set

    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch {
            try {
                uiState = HomeState.Success(repository.getPokemon())
            } catch (e: IOException) {
                uiState = HomeState.Error
            }
        }
    }

}

