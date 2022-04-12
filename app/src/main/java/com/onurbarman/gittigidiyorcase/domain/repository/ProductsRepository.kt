package com.onurbarman.gittigidiyorcase.domain.repository

import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import kotlinx.coroutines.flow.Flow

/**
 * Interface of Products Repository to expose to other module
 */
interface ProductsRepository {

    suspend fun fetchToken(): Flow<Output<TokenResponse>>

    suspend fun fetchHomePage(token: String): Flow<Output<HomePageResponse>>

    suspend fun fetchProductDetail(token: String, id: Int): Flow<Output<ProductDetailResponse>>
}