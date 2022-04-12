package com.onurbarman.gittigidiyorcase.domain.usecase

import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Products UseCase to expose to ui module
 */
interface ProductsUseCase {
    /**
     * UseCase Method to fetch the products from Data Layer
     */
    suspend fun executeToken(): Flow<Output<TokenResponse>>

    suspend fun executeHomePage(token: String): Flow<Output<HomePageResponse>>

    suspend fun executeProductDetail(token: String, id: Int): Flow<Output<ProductDetailResponse>>
}