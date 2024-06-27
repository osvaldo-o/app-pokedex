package fes.aragon.apppokedex.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fes.aragon.apppokedex.data.datasource.remote.PokeApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun providerRetrofit() = Retrofit
        .Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://pokeapi.co/api/v2/")
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PokeApiService = retrofit.create(PokeApiService::class.java)

}