package fes.aragon.apppokedex.ui.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fes.aragon.apppokedex.R
import fes.aragon.apppokedex.ui.components.ItemPokemon
import fes.aragon.apppokedex.ui.components.TopBar

@Composable
fun FavoriteScreen(
    uiState: FavoriteState,
    navDetailScreen: (Int) -> Unit
) {
    Scaffold(
        topBar = { TopBar(title = stringResource(id = R.string.title_favorite_screen)) }
    ) {
        if (uiState.pokemonFavorites.isNotEmpty()) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.pokemonFavorites) {
                    ItemPokemon(
                        name = it.name,
                        urlImage = stringResource(id = R.string.url_base_image)+"${it.id}.png",
                        onClick = { navDetailScreen(it.id) }
                    )
                }
            }
        } else {
            MessageEmptyFavorite(modifier = Modifier.padding(it))
        }
    }
}

@Composable
fun MessageEmptyFavorite(
    modifier: Modifier
) {
    Box(modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.message_empty_favorire),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}