package com.patrimesp.mymovies.data.di

import com.patrimesp.mymovies.BuildConfig
import com.patrimesp.mymovies.data.datasource.api.ApiConfig.BASE_URL
import com.patrimesp.mymovies.data.datasource.api.ApiService
import com.patrimesp.mymovies.data.repository.MovieRepositoryImpl
import com.patrimesp.mymovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(api: ApiService): MovieRepository = MovieRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val authenticatedUrl = request.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build()

                chain.proceed(
                    request.newBuilder()
                        .url(authenticatedUrl)
                        .header("Authorization", "Bearer ${BuildConfig.TMDB_BEARER_TOKEN}")
                        .header("accept", "application/json")
                        .build()
                )
            }
            .build()
    }

    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

}
