package com.mharis404.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {
    fun registerGraph(
        navGraphBuilder:NavGraphBuilder,
        navHostController: NavHostController
    )
}