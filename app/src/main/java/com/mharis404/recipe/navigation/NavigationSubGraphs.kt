package com.mharis404.recipe.navigation

import com.mharis404.media_player.navigation.MediaPlayerFeatureAPi
import com.mharis404.search.navigation.SearchFeatureApi

data class NavigationSubGraphs(
    val searchFeatureApi: SearchFeatureApi,
    val mediaPlayerApi:MediaPlayerFeatureAPi
)
