package com.mharis404.search.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mharis404.common.navigation.FeatureApi
import com.mharis404.common.navigation.NavigationRoute
import com.mharis404.common.navigation.NavigationSubGraphRoute
import com.mharis404.search.screens.details.RecipeDetails
import com.mharis404.search.screens.details.RecipeDetailsScreen
import com.mharis404.search.screens.details.RecipeDetailsViewModel
import com.mharis404.search.screens.favorite.FavoriteScreen
import com.mharis404.search.screens.favorite.FavoriteViewModel
import com.mharis404.search.screens.recipe_list.RecipeList
import com.mharis404.search.screens.recipe_list.RecipeListScreen
import com.mharis404.search.screens.recipe_list.RecipeListViewModel

interface SearchFeatureApi : FeatureApi

class SearchFeatureApiImpl : SearchFeatureApi {
    override fun registerGraph(
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        navHostController: androidx.navigation.NavHostController
    ) {
        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavigationRoute.RecipeList.route
        ) {

            composable(route = NavigationRoute.RecipeList.route) {
                val viewModel = hiltViewModel<RecipeListViewModel>()
                RecipeListScreen(
                    viewModel = viewModel,
                    navHostController = navHostController
                ) { mealId ->
                    viewModel.onEvent(RecipeList.Event.GoToRecipeDetails(mealId))
                }

            }

            composable(route = NavigationRoute.RecipeDetails.route) {
                val viewModel = hiltViewModel<RecipeDetailsViewModel>()
                val mealId = it.arguments?.getString("id")
                LaunchedEffect(key1 = mealId) {
                    mealId?.let {
                        viewModel.onEvent(RecipeDetails.Event.FetchRecipeDetails(it))
                    }
                }
                RecipeDetailsScreen(
                    viewModel = viewModel,
                    onNavigationClick = {
                        viewModel.onEvent(RecipeDetails.Event.GoToRecipeListScreen)
                    },
                    onFavoriteClick = {
                        viewModel.onEvent(RecipeDetails.Event.InsertRecipe(it))
                    },
                    onDelete = {
                        viewModel.onEvent(RecipeDetails.Event.DeleteRecipe(it))
                    }, navHostController = navHostController
                )
            }

            composable(NavigationRoute.FavoriteScreen.route) {
                val viewModel = hiltViewModel<FavoriteViewModel>()
                FavoriteScreen(
                    navHostController = navHostController,
                    viewModel = viewModel,
                    onClick = { mealId ->
                        viewModel.onEvent(FavoriteScreen.Event.GoToDetails(mealId))
                    })
            }

        }


    }
}

