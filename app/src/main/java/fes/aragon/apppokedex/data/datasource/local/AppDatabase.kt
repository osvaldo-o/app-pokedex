package fes.aragon.apppokedex.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import fes.aragon.apppokedex.model.PokemonEntity

@Database(entities = [PokemonEntity::class], exportSchema = true, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}