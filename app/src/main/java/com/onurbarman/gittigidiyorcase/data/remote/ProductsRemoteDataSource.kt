package com.onurbarman.gittigidiyorcase.data.remote

import com.onurbarman.gittigidiyorcase.data.ApiMainHeadersProvider
import com.onurbarman.gittigidiyorcase.data.BaseRemoteDataSource
import com.onurbarman.gittigidiyorcase.data.services.ApiService
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.Output
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * RemoteDataSource of Products API service
 * @param apiService the object of api service
 */
class ProductsRemoteDataSource @Inject constructor(
    private val apiService: ApiService, retrofit: Retrofit,
    private val headersProvider: ApiMainHeadersProvider
) : BaseRemoteDataSource(retrofit) {

    suspend fun fetchToken(): Output<TokenResponse> {
        return getResponse(
            request = { apiService.postInitialize() },
            defaultErrorMessage = "Error fetching Token"
        )
    }

    suspend fun fetchHomePage(token: String): Output<HomePageResponse> {
        return getResponse(
            request = { apiService.getHomePage(headersProvider.getAuthenticatedHeaders(token)) },
            defaultErrorMessage = "Error fetching Homepage"
        )
    }

    suspend fun fetchProductDetail(token: String, id: Int): Output<ProductDetailResponse> {
        return getResponse(
            request = { apiService.getProductDetail(headersProvider.getAuthenticatedHeaders(token), id) },
            defaultErrorMessage = "Error fetching Product"
        )
    }
}