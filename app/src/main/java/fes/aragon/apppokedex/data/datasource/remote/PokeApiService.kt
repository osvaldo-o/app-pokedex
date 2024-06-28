package fes.aragon.apppokedex.data.datasource.remote

import fes.aragon.apppokedex.model.PokemonDetail
import fes.aragon.apppokedex.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemon(@Query("limit") limit: String = "251"): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetail

}