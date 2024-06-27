package fes.aragon.apppokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import fes.aragon.apppokedex.ui.PokeApp
import fes.aragon.apppokedex.ui.theme.AppPokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPokedexTheme {
                PokeApp()
            }
        }
    }
}