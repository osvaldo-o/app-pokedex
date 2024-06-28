package fes.aragon.apppokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fes.aragon.apppokedex.ui.screens.detail.DetailScreen
import fes.aragon.apppokedex.ui.screens.detail.DetailViewModel
import fes.aragon.apppokedex.ui.screens.favorite.FavoriteScreen
import fes.aragon.apppokedex.ui.screens.favorite.FavoriteViewModel
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
                navDetailScreen = { navHostController.navigate(Screens.DetailScreen.name+"/$it") },
                navFavoriteScreen = { navHostController.navigate(Screens.FavoriteScreen.name) },
                uiState = viewModel.uiState
            )
        }
        composable(
            Screens.DetailScreen.name+"/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType})
        ) { backStackEntry ->
            val viewModel = hiltViewModel<DetailViewModel>()
            val uiState = viewModel.uiState.collectAsState().value
            DetailScreen(
                id = backStackEntry.arguments!!.getInt("id"),
                onEvent = viewModel::onEvent,
                navigateUp = { navHostController.navigateUp() },
                uiState = uiState
            )
        }
        composable(Screens.FavoriteScreen.name) {
            val viewModel = hiltViewModel<FavoriteViewModel>()
            val uiState = viewModel.uiState.collectAsState().value
            FavoriteScreen(
                uiState = uiState,
                navDetailScreen = { navHostController.navigate(Screens.DetailScreen.name+"/$it") }
            )
        }
    }
}

enum class Screens {
    HomeScreen,
    DetailScreen,
    FavoriteScreen
}