package com.onurbarman.gittigidiyorcase.data.services


import com.onurbarman.gittigidiyorcase.BuildConfig
import com.onurbarman.gittigidiyorcase.data.AuthenticatedHeaders
import com.onurbarman.gittigidiyorcase.domain.model.HomePageResponse
import com.onurbarman.gittigidiyorcase.domain.model.ProductDetailResponse
import com.onurbarman.gittigidiyorcase.domain.model.TokenResponse
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit API Service
 */
interface ApiService {

    @POST("initialize")
    @FormUrlEncoded
    suspend fun postInitialize(
        @Field("appVersion") appVersion: String = BuildConfig.VERSION_NAME
    ): Response<TokenResponse>

    @GET("home-page")
    suspend fun getHomePage(
        @HeaderMap authedHeaders: AuthenticatedHeaders
    ): Response<HomePageResponse>

    @GET("product/detail/{id}")
    suspend fun getProductDetail(
        @HeaderMap authedHeaders: AuthenticatedHeaders,
        @Path("id") id: Int
    ): Response<ProductDetailResponse>
}