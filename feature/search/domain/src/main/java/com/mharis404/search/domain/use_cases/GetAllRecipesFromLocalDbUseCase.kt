package com.mharis404.search.domain.use_cases

import com.mharis404.search.domain.repository.SearchRepository
import javax.inject.Inject

class GetAllRecipesFromLocalDbUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    operator fun invoke() = searchRepository.getAllRecipes()
}