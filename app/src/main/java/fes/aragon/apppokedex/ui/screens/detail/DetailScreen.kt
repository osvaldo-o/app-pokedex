package fes.aragon.apppokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fes.aragon.apppokedex.R
import fes.aragon.apppokedex.model.PokemonDetail
import fes.aragon.apppokedex.model.TypePokemon
import fes.aragon.apppokedex.ui.components.ErrorScreen
import fes.aragon.apppokedex.ui.components.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: Int,
    onEvent: (DetailEvent) -> Unit,
    navigateUp: () -> Unit,
    uiState: DetailState
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.icon_back),
                        tint = Color.White
                    )
                }
            },
            actions = {
                Icon(
                    imageVector = if (uiState.isFavorite) Icons.Default.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.icon_favorite),
                    tint = Color.White,
                    modifier = Modifier.padding(end = 16.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary))
        }
    ) {
        when(uiState.pokemon) {
            State.Error -> { ErrorScreen(modifier = Modifier.padding(it)) }
            State.Loading -> { LoadingScreen(modifier = Modifier.padding(it)) }
            is State.Success<*> -> {
                DetailContent(
                    id = id,
                    pokemon = uiState.pokemon.data as PokemonDetail,
                    isFavorite = uiState.isFavorite,
                    modifier = Modifier.padding(it),
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun DetailContent(
    id: Int,
    pokemon: PokemonDetail,
    isFavorite: Boolean,
    onEvent: (DetailEvent) -> Unit,
    modifier: Modifier
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(0.4f))
            Spacer(
                Modifier
                    .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    .background(Color.White)
                    .weight(0.6f)
                    .fillMaxWidth()
            )
        }
        Column(
            Modifier
                .align(Alignment.TopCenter),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = pokemon.name.replaceFirstChar { char -> char.uppercase() }, style = MaterialTheme.typography.headlineLarge, color = Color.White, fontWeight = FontWeight.Bold)
                Text(text = "#${String.format("%03d", id)}", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            }
            AsyncImage(
                model = stringResource(id = R.string.url_base_image)+"$id.png",
                contentDescription = stringResource(id = R.string.image_pokemon),
                modifier = Modifier
                    .height(320.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Column(
                Modifier.padding(horizontal = 32.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoView(key = stringResource(id = R.string.height), value = pokemon.height.toString())
                InfoView(key = stringResource(id = R.string.weight), value = pokemon.weight.toString())
                InfoView(key = stringResource(id = R.string.base_experience), value = pokemon.base_experience.toString())
                InfoView(key = stringResource(id = R.string.type), value = getTypeString(pokemon.types))
                Button(
                    onClick = { if (isFavorite) onEvent(DetailEvent.DeleteFavorite(pokemon.name)) else onEvent(DetailEvent.AddFavorite(pokemon.name)) },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text(
                        text = if(isFavorite) stringResource(id = R.string.delete_favorite) else stringResource(id = R.string.add_favorite),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@Composable
fun InfoView(
    key: String,
    value: String
) {
    val style = MaterialTheme.typography.titleMedium
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = key, style = style, fontWeight = FontWeight.Bold)
        Text(text = value, style = style, color = Color.Gray)
    }
}

fun getTypeString(types: List<TypePokemon>): String {
    var typeString = types[0].type.name
    types.subList(1,types.size).forEach {
        typeString += ", ${it.type.name}"
    }
    return typeString
}
