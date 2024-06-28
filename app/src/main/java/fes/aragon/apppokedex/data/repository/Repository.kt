package fes.aragon.apppokedex.data.repository

import fes.aragon.apppokedex.data.datasource.local.PokemonDao
import fes.aragon.apppokedex.model.PokemonEntity
import fes.aragon.apppokedex.data.datasource.remote.PokeApiService
import fes.aragon.apppokedex.model.PokemonApi
import fes.aragon.apppokedex.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val pokeApiService: PokeApiService,
    private val pokemonDao: PokemonDao
) {
    suspend fun getPokemon(): List<PokemonApi> = pokeApiService.getPokemon().results
    suspend fun getPokemonDetail(id: Int): PokemonDetail = pokeApiService.getPokemonDetail(id)
    fun getPokemonFavorite(): Flow<List<PokemonEntity>> = pokemonDao.getPokemonFavorites()
    suspend fun isPokemonFavorite(id: Int): Boolean = pokemonDao.isPokemonFavorite(id) > 0
    suspend fun insertPokemon(pokemonEntity: PokemonEntity) = pokemonDao.insertPokemon(pokemonEntity)
    suspend fun deletePokemon(pokemonEntity: PokemonEntity) = pokemonDao.deletePokemon(pokemonEntity)
}