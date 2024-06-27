package fes.aragon.apppokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_favorite")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
)
