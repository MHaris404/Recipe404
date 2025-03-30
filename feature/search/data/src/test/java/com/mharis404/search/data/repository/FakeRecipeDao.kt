package com.mharis404.search.data.repository

import com.mharis404.search.data.local.RecipeDao
import com.mharis404.search.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRecipeDao : RecipeDao {

    val list = mutableListOf<Recipe>()

    override suspend fun insertRecipe(recipe: Recipe) {
        list.add(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        list.remove(recipe)
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return flowOf(list)
    }

    override suspend fun updateRecipe(recipe: Recipe) {

    }
}