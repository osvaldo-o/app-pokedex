package fes.aragon.apppokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fes.aragon.apppokedex.ui.screens.home.HomeScreen
import fes.aragon.apppokedex.ui.screens.home.HomeViewModel

@Composable
fun PokeApp(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.name,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screens.HomeScreen.name) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                navDetailScreen = { },
                navFavoriteScreen = { },
                uiState = viewModel.uiState
            )
        }
    }
}

enum class Screens {
    HomeScreen,
    DetailScreen,
    FavoriteScreen
}