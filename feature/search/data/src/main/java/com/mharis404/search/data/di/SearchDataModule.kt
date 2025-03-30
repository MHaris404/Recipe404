package com.mharis404.search.data.di

import com.mharis404.search.data.local.RecipeDao
import com.mharis404.search.data.remote.SearchApiService
import com.mharis404.search.data.repository.SearchRepoImpl
import com.mharis404.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://www.themealdb.com/"

@InstallIn(SingletonComponent::class)
@Module
object SearchDataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideSearchApiService(retrofit: Retrofit): SearchApiService {
        return retrofit.create(SearchApiService::class.java)
    }

    @Provides
    fun provideSearchRepo(
        searchApiService: SearchApiService,
        recipeDao: RecipeDao
    ): SearchRepository {
        return SearchRepoImpl(searchApiService, recipeDao)
    }

}