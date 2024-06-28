package fes.aragon.apppokedex.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fes.aragon.apppokedex.R
import fes.aragon.apppokedex.model.PokemonApi
import fes.aragon.apppokedex.ui.components.ErrorScreen
import fes.aragon.apppokedex.ui.components.ItemPokemon
import fes.aragon.apppokedex.ui.components.LoadingScreen
import fes.aragon.apppokedex.ui.components.TopBar

@Composable
fun HomeScreen(
    navDetailScreen: (Int) -> Unit,
    navFavoriteScreen: () -> Unit,
    uiState: HomeState
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.title_home_screen))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navFavoriteScreen,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.favorite_icon),
                    tint = Color.White
                )
            }
        }
    ) {
        Surface(Modifier.padding(it)) {
            when(uiState) {
                HomeState.Error -> { ErrorScreen() }
                HomeState.Loading -> { LoadingScreen() }
                is HomeState.Success -> {
                    HomeContent(
                        pokemonList = uiState.pokemon,
                        navDetailScreen = navDetailScreen
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    pokemonList: List<PokemonApi>,
    navDetailScreen: (Int) -> Unit
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(pokemonList) { index, item ->
            ItemPokemon(
                name = item.name,
                urlImage = stringResource(id = R.string.url_base_image)+"${index+1}.png",
                onClick = { navDetailScreen(index+1) }
            )
        }
    }
}