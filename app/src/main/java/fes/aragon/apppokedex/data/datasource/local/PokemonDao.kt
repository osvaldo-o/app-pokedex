package fes.aragon.apppokedex.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import fes.aragon.apppokedex.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Upsert
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)

    @Query("SELECT * FROM pokemon_favorite")
    fun getPokemonFavorites(): Flow<List<PokemonEntity>>

}