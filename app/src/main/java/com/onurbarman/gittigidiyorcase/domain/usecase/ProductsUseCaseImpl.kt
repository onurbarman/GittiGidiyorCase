package com.onurbarman.gittigidiyorcase.domain.usecase

import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import com.onurbarman.gittigidiyorcase.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of Products UseCase
 * @param productsRepository the products repository object
 */
internal class ProductsUseCaseImpl @Inject constructor(private val productsRepository: ProductsRepository) :
    ProductsUseCase {


    override suspend fun executeToken(): Flow<Output<TokenResponse>> {
        return productsRepository.fetchToken()
    }

    override suspend fun executeHomePage(token: String): Flow<Output<HomePageResponse>> {
        return productsRepository.fetchHomePage(token)
    }

    override suspend fun executeProductDetail(token: String,id: Int): Flow<Output<ProductDetailResponse>> {
        return productsRepository.fetchProductDetail(token,id)
    }
}